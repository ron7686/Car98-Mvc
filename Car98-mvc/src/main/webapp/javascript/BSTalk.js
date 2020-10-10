$(document).ready(function(){
				$('#headtitle').textillate({
                    loop: true,
                    minDisplayTime: 1000,
                    in: {
                        effect: 'rotateInDownLeft',
                        delayScale: 5.0,
                        delay: 50,
                        sync: false,
                        reverse: false,
                        shuffle: false,
                        callback: function () {}
                    },
                    out: {
                        effect: 'rotateInDownLeft',
                        delayScale: 5.0,
                        delay: 50,
                        sync: false,
                        reverse: false,
                        shuffle: false,
                        callback: function () {}
                    },
                });
			});			