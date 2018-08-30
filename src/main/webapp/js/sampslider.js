	(function(jQuery){	
	
		var settings = {};
		var sWidth = 0, sHeight = 0, sRatio = 0;//dimension				
		var curSlide = 1, toSlide = 1, totalSlides = 1;
		var handleClick = null;//custom click function if needed		
		var	playTimer;//autoplay timer
		var timeoutId = null, moveSpeed = 0, movedir = 1;//scrolling vars
		
		
		_sampSlider = function(elem){		
							
			_self = this;
			totalSlides = jQuery(elem+'.samp-slider .samp-container-horizontal').children().length;
			var isTouchSupported = ('ontouchstart' in window);						
			var startEvent = isTouchSupported ? 'touchstart' : 'mousedown';
			var moveEvent = isTouchSupported ? 'touchmove' : 'mousemove';
			var endEvent = isTouchSupported ? 'touchend' : 'mouseup';

        if(jQuery(elem+'.samp-slider').find('.slide-horizontal').length == 0) jQuery(elem+'.samp-slider').append('<a class="slide-horizontal slide-horizontal-prev"></a><a class="slide-horizontal slide-horizontal-next"></a>');

				jQuery(elem+'.samp-slider .slide-horizontal').click(function(e){	
						
					e.preventDefault();
						jQuery(elem+'.samp-slider .slide-horizontal').fadeOut(50);
						//check for external function
						if(typeof _self.handleClick == 'function') _self.handleClick(e.target);																							
						else	_clickHandler(e.target);
				});			
						
						
				//if(settings.dragonbar){
				//Drag On							
						//if(jQuery(elem+'.samp-slider').find('.samp-slider-dragon').length == 0) jQuery(elem+'.samp-slider').append('<div class="samp-slider-dragon"><a class="samp-slider-dragon-ctrl"></a><div class="dragon-line"></div></div>');   

		//				jQuery(elem+' .samp-slider-dragon .samp-slider-dragon-ctrl').on('touchstart', _mouseListeners);
//						jQuery(elem+' .samp-slider-dragon .samp-slider-dragon-ctrl').on('touchmove', _mouseListeners);	
//						jQuery(elem+' .samp-slider-dragon .samp-slider-dragon-ctrl').on('touchend', _mouseListeners);
//						jQuery(elem+' .samp-slider-dragon .samp-slider-dragon-ctrl').on('mousedown', _mouseListeners);
//						jQuery(elem+' .samp-slider-dragon .samp-slider-dragon-ctrl').on('mousemove', _mouseListeners);
//						jQuery(elem+' .samp-slider-dragon .samp-slider-dragon-ctrl').on('mouseup', _mouseListeners);														
//
//				}

						
			function _mouseListeners(e){
				e = (e || event);								
				_stopDefault(e);//e.preventDefault();	
				_autoPlayHandler(false);//if clicked, disable autoplay timer
				
					if(e.type.indexOf('touch') > -1) isTouchSupported = true;
					else isTouchSupported = false;

				startEvent = isTouchSupported ? 'touchstart' : 'mousedown';
				moveEvent = isTouchSupported ? 'touchmove' : 'mousemove';
				endEvent = isTouchSupported ? 'touchend' : 'mouseup';						

				var tgt = e.currentTarget;												
					
					if(e.type === startEvent){									
				
							jQuery(tgt).parent().on(moveEvent, _dragonHandler);																										
						window.addEventListener(endEvent,  _mouseListeners);																		 												

					}
					
					if(e.type === endEvent){
						
						timeoutId = clearTimeout(timeoutId);
						window.removeEventListener(e.type, _mouseListeners, false);
																										
							tgt = jQuery(elem+'.samp-slider').find('.samp-slider-dragon');
													
							tgt.off(moveEvent, _dragonHandler, true);
						var drgtgt = tgt.find('.samp-slider-dragon-ctrl');
						drgtgt.show();																			
						var curPos = drgtgt.offset();		
						curPos.left = (curPos.left-tgt.offset().left)-parseInt(drgtgt.css('margin-left').replace('px',''));	
						
						var sldW = jQuery(elem+'.samp-slider').find('.samp-slider-dragon').width();	
							
							if(moveSpeed > 0){
								//nada now, some stuff later
							}else{
																													
									if(curPos.left < sldW/2) toSlide--;
									else if(curPos.left >= sldW/2) toSlide++;

							}
		
						gotoSlide(toSlide);	
											
							jQuery(drgtgt).next().animate({'left':sldW/2,'width':0}, (settings.slidespeed/2));
							
							drgtgt.animate({'left':sldW/2}, (settings.slidespeed/2), function(){
								
									if(drgtgt.hasClass('samp-slider-dragon-ctrl')) drgtgt.attr('class', 'samp-slider-dragon-ctrl');
							});
					}
					
			}


			function _dragonHandler(e){
				e = (e || event);//console.log(e);				
				var oSet = 0;				
				var tgt = e.currentTarget;
				var tgtW = jQuery(tgt).width();
				var ctr = tgtW/2;
				var icondir = 'next';					
				rect = tgt.getBoundingClientRect();//console.log(rect.top, rect.right, rect.bottom, rect.left);

				var dX = e.clientX-rect.left;							
				moveSpeed = 0;
											
					if(isTouchSupported){
						dX = e.originalEvent.touches[0].clientX-rect.left;
					}
	
				var minOset = jQuery(tgt.children[0]).width()/2;
				var maxOset = jQuery(tgt).width()-(jQuery(tgt.children[0]).width()/2);
				
					if(dX <= minOset) dX = minOset;
					if(dX >= maxOset) dX = maxOset;
					
				tgt.children[0].style.left = dX+'px';								
				oSet = Math.abs(ctr-dX);
			
					if((ctr-dX) > 0){
						icondir = 'prev';
						movedir = 1;
					}else{
						movedir = -1;										 
					}
					
					//if half way and higher than 20th of width
					if(oSet <= tgtW/4 && oSet > tgtW/20){
						
						jQuery(tgt.children[0]).attr('class', 'samp-slider-dragon-ctrl');
						jQuery(tgt.children[0]).addClass('dragon-slide-'+icondir);
						
					}else if(oSet > tgtW/4){
						moveSpeed = 10;
							jQuery(tgt.children[0]).attr('class', 'samp-slider-dragon-ctrl');
							jQuery(tgt.children[0]).addClass('dragon-move-'+icondir);
						
					}

					if(oSet >= ((tgtW/2)*0.75)){
						moveSpeed = 50;
							jQuery(tgt.children[0]).attr('class', 'samp-slider-dragon-ctrl');
							jQuery(tgt.children[0]).addClass('dragon-frw-'+icondir);
						
					}			
							
					jQuery(tgt.children[0]).addClass('samp-grab');	
					
					if(moveSpeed > 0){									
							if(timeoutId == null)	timeoutId = window.setTimeout(scrollX, 50);
					}else	timeoutId = clearTimeout(timeoutId);
																
					if(dX < ctr) {
						tgt.children[1].style.width = ((tgtW/2)-dX)+'px';
						tgt.children[1].style.left = dX+'px';
					}else{
						tgt.children[1].style.left = (tgtW/2)+'px';
						tgt.children[1].style.width = (dX-(tgtW/2))+'px';
					}

			}
						
			
			
			function scrollX(){

				var curX = movedir*parseInt(jQuery(elem+'.samp-slider .samp-container-horizontal').css('margin-left').replace('px',''));					
				var newX = ((curX+moveSpeed)*movedir);
				var maxOffset = -((sWidth*totalSlides)-sWidth);

					if(newX >= 0){
						 newX = 0;
						 jQuery(elem).find('.samp-slider-dragon-ctrl').hide();
					}else if(newX <= maxOffset){
						 newX = maxOffset;
						 jQuery(elem).find('.samp-slider-dragon-ctrl').hide();
					}
					
				toSlide = Math.abs(Math.floor((curX+(sWidth/2))/sWidth))+1;	
	
					jQuery(elem+'.samp-slider .samp-container-horizontal').css({'margin-left':+newX+'px'});

				timeoutId = window.setTimeout(scrollX, 50);
			
			}
	
			
			function _clickHandler(_this){
			
				var mTarget = elem;
				_autoPlayHandler(false);//if clicked, disable autoplay timer
				
					if(jQuery(_this).attr('href') !== '' && jQuery(_this).attr('href') !== undefined) mTarget = jQuery(_this).attr('href');							
			
					if(jQuery(_this).attr('rel') !== '' && jQuery(_this).attr('rel') !== undefined) toSlide = jQuery(_this).attr('rel')-1;
					else{

							if(jQuery(_this).hasClass('slide-horizontal-next'))	toSlide++;
							else if(jQuery(_this).hasClass('slide-horizontal-prev')) toSlide--;
					}

				gotoSlide(toSlide);
			}

			
			function _stopDefault(e){
				
					if (e && e.preventDefault) e.preventDefault();
					else	window.event.returnValue = false;
			
				return false;
			}
			
			
			function _animateThis(mTarget, duration){				
															
					if(settings.loop){
						
						var dotransit = false;									
			
							if(toSlide > totalSlides){																
								toSlide = 1;	
								dotransit = true;														
							}
				 
							if(curSlide < 1){							 
								toSlide = totalSlides;
								dotransit = true;													
							}
							
							if(dotransit){

									jQuery(elem+'.samp-slider .samp-container-horizontal').animate({'opacity':0}, settings.slidespeed/4, function(){
							
											jQuery(elem+'.samp-slider .samp-container-horizontal').animate({'opacity':1}, settings.slidespeed/4, function(){
													jQuery(elem+'.samp-slider .samp-container-horizontal').parent().removeClass('wait');
											});
													
									}).parent().addClass('wait');
											
							}
									
					}													

				var mOffset = (jQuery(mTarget).outerWidth());																
				var goOffset = -(mOffset * (toSlide-1));
					
					jQuery(elem+'.samp-slider .samp-container-horizontal').animate({'marginLeft': goOffset}, duration, function(){					

						curSlide = toSlide;
					
							jQuery(elem+'.samp-slider .slide-horizontal').fadeIn(settings.slidespeed/4);
					
							if(curSlide <= 1 && !settings.loop) jQuery(elem+'.samp-slider .slide-horizontal.slide-horizontal-prev').hide();
							else jQuery(elem+'.samp-slider .slide-horizontal.slide-horizontal-prev').fadeIn(settings.slidespeed/4);									
								
							//jQuery(elem+'.samp-slider .samp-container-horizontal .samp-container').css({'opacity':'0.8'});//, settings.slidespeed/4			
							//jQuery(elem+'.samp-slider .samp-container-horizontal .samp-container:nth-child('+curSlide+')').animate({'opacity':'1'}, settings.slidespeed/4);									
							
							if(settings.gototop) jQuery('html, body').stop().animate({scrollTop: jQuery(mTarget).offset().top}, settings.slidespeed/2);

					});		
				
			}
		
		
			function getCurrentSlide(){
				return curSlide;				
			}	
			
			
			function getTotalSlides(){
				return totalSlides;				
			}		
				
								
			function gotoSlide(slideNo){	

				toSlide = parseInt(slideNo);
				
					if(toSlide < 1 && !settings.loop) toSlide = totalSlides;	
					else if(toSlide > totalSlides && !settings.loop) toSlide = 1;
						
				curSlide = toSlide;	
				_animateThis(elem, settings.slidespeed);
					
			}			
							
			function _autoPlayHandler(g){
					
					if(settings.autoplay && g){
						
						playTimer = setTimeout(function(){				
							toSlide++;
							gotoSlide(toSlide);
							_autoPlayHandler(true);	
								
						}, settings.autoplay);
								
					}else playTimer =  clearTimeout(playTimer);	
			}
		//resize
			var resizeTimer;					
				
				jQuery(window).on('resize', function(){	
				
						if(resizeTimer)	clearTimeout(resizeTimer);			

					resizeTimer = setTimeout(function() {								
						_setupSampSlider(elem);	
					}, 50);			
				});			

				if(settings.autoplay)	_autoPlayHandler(true);

				jQuery(window).resize();
										
		settings.afterinit();
		gotoSlide(settings.startslide);
		return this;
	}				

				
	function _setupSampSlider(elem){
	
		sWidth = settings.width;
		sHeight = settings.height;
		sRatio = 0;
			
			if(settings.autosize){
				sWidth = jQuery(elem+'.samp-slider').parent().width();//width of the holder
				sHeight = jQuery(elem+'.samp-slider').parent().height();
				sRatio = sWidth/sHeight;										
			}
			
			if(settings.aspectratio) sRatio = settings.aspectratio;//overrides autosize		
					
	
		sHeight	= Math.round(sWidth/sRatio);




				if(settings.dragonbar){
				//Drag On							

						jQuery(elem+'.samp-slider').find('.samp-slider-dragon').find('.samp-slider-dragon-ctrl').css({'left':(jQuery(elem+'.samp-slider').find('.samp-slider-dragon').width()/2)+'px'});
				
				}





		var totsWidth = (sWidth * jQuery(elem+'.samp-slider .samp-container-horizontal .samp-container').length);
				
			if(totsWidth > 0){						
					jQuery(elem+'.samp-slider .samp-container-horizontal .samp-container').css({'width': sWidth + 'px','height':sHeight + 'px'});
					jQuery(elem+'.samp-slider .samp-container-horizontal').css({'width':totsWidth+50 + 'px','height':sHeight,'margin-left':-(sWidth*(curSlide-1))+'px'});
			}						
	
	}


		jQuery.fn.sampSlider = function(options){			
			/*options*/
			settings = jQuery.extend({				
				autosize: true,
				width: 900,
				height: 600,//width overrides height with aspect ratio
				aspectratio: false,//overrides autosize. when false it is calculated
				autoplay: 0,//0 or delay in milliseconds
				dragonbar:false,//true to show drag-navi
				arrows:true,
				loop: true,//Boolean, infinite loop
				slidespeed: 500,
				startslide: 1,// 1 and up
				gototop: false, //boolean, if automatically scroll to top of slider
				afterinit: function(){
					console.log('Vroom.');
				}
			}, options);
				
			var selected = this;
			_setupSampSlider(selected.selector);
			return _sampSlider(this.selector); 			 						 
		
		};		
		
		/*jQuery.fn.sampSlider.defaults = {}; default options end */				

})(jQuery);