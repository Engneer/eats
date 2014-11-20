//jq(document).ready(function(){
	function ready(){
	var camera = jq('#camera'),
		photos = jq('#photos'),
		screen =  jq('#screen');

	var template = '<img src="./uploads/original/{src}" rel="cam" '
		+'style="background-image:url(./uploads/original/{src});width:200px;height:200px"></img>';

	/*----------------------------------
		Setting up the web camera
	----------------------------------*/


	//webcam.set_swf_url('assets/webcam/webcam.swf');
	webcam.set_swf_url('./assets/webcam/webcam.swf');
	webcam.set_api_url('./upload.jsp');	// The upload script
	webcam.set_quality(90);				// JPEG Photo Quality
	webcam.set_shutter_sound(true, './assets/webcam/shutter.mp3');

	// Generating the embed code and adding it to the page:	
	screen.html(
		webcam.get_html(screen.width(), screen.height())
	);


	/*----------------------------------
		Binding event listeners
	----------------------------------*/


	var shootEnabled = false;
		
	jq('#shootButton').click(function(){
		
		if(!shootEnabled){
			return false;
		}
		console.log("4546");
		webcam.freeze();
		togglePane();
		return false;
	});
	
	jq('#cancelButton').click(function(){
		webcam.reset();
		togglePane();
		return false;
	});
	
	jq('#uploadButton').click(function(){
		webcam.upload();
		webcam.reset();
		togglePane();
		return false;
	});

	camera.find('.settings').click(function(){
		if(!shootEnabled){
			return false;
		}
		
		webcam.configure('camera');
	});

	// Showing and hiding the camera panel:
	
	var shown = false;
	/* jq('.camTop').click(function(){
		
		jq('.tooltip').fadeOut('fast');
		
		if(shown){
			camera.animate({
				bottom:-466
			});
		}
		else {
			camera.animate({
				bottom:-5
			},{easing:'easeOutExpo',duration:'slow'});
		}
		
		shown = !shown;
	}); */

	jq('.tooltip').mouseenter(function(){
		jq(this).fadeOut('fast');
	});


	/*---------------------- 
		Callbacks
	----------------------*/
	
	
	webcam.set_hook('onLoad',function(){
		// When the flash loads, enable
		// the Shoot and settings buttons:
		shootEnabled = true;
	});
	
	webcam.set_hook('onComplete', function(msg){
		
		// This response is returned by upload.php
		// and it holds the name of the image in a
		// JSON object format:
		console.log(msg);
		msg = jq.parseJSON(msg);
		
		//console.log(msg.error);
		if(msg.error){
			alert(msg.message);
		}
		else {
			// Adding it to the page;
			photos.html(templateReplace(template,{src:msg.filename}));
			initFancyBox();
		}
	});
	
	webcam.set_hook('onError',function(e){
		screen.html(e);
	});
	
	
	/*-------------------------------------
		Populating the page with images
	-------------------------------------*/
	
	var start = '';
	
	function loadPics(){
	
		// This is true when loadPics is called
		// as an event handler for the LoadMore button:
		
		if(this != window){
			if(jq(this).html() == 'Loading..'){
				// Preventing more than one click
				return false;
			}
			jq(this).html('Loading..');
		}
		
		// Issuing an AJAX request. The start parameter
		// is either empty or holds the name of the first
		// image to be displayed. Useful for pagination:
		
		jq.getJSON('browse.php',{'start':start},function(r){
			
			photos.find('a').show();
			var loadMore = jq('$loadMore').detach();
			
			if(!loadMore.length){
				loadMore = jq('<span>',{
					id			: 'loadMore',
					html		: 'Load More',
					click		: loadPics
				});
			}
			
			jq.each(r.m,function(i,filename){
				photos.append(templateReplace(template,{src:filename}));
			});

			// If there is a next page with images:			
			if(r.nextStart){
				
				// r.nextStart holds the name of the image
				// that comes after the last one shown currently.
				
				start = r.nextStart;
				photos.find('a:last').hide();
				photos.append(loadMore.html('Load More'));
			}
			
			// We have to re-initialize fancybox every
			// time we add new photos to the page:
			
			initFancyBox();
		});
		
		return false;
	}

	// Automatically calling loadPics to
	// populate the page onload:
	
	//loadPics();
	

	/*----------------------
		Helper functions
	------------------------*/

	
	// This function initializes the
	// fancybox lightbox script.
	
	function initFancyBox(filename){
		photos.find('a:visible').fancybox({
			'transitionIn'	: 'elastic',
			'transitionOut'	: 'elastic',
			'overlayColor'	: '#111'
		});
	}


	// This function toggles the two
	// .buttonPane divs into visibility:
	
	function togglePane(){
		var visible = jq('#camera .buttonPane:visible:first');
		var hidden = jq('#camera .buttonPane:hidden:first');
		
		visible.fadeOut('fast',function(){
			hidden.show();
		});
	}
	
	
	// Helper function for replacing "{KEYWORD}" with
	// the respectful values of an object:
	
	function templateReplace(template,data){
		return template.replace(/{([^}]+)}/g,function(match,group){
			//console.log(template);
			//console.log(data);
			//console.log(data[group.toLowerCase()]);
			return data[group.toLowerCase()];
		});
	}
//});
	}
