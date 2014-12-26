$(window).on('load', 	function(){
	var $startButton = $('#startButton');
	var $pauseButton = $('#pauseButton');
	var $stopButton = $('#stopButton');
	var $currentTime = $('#currentTime');
	var $submitForm = $('#submit');
	var $submitButton = $('#submitButton');

	timer = new Stopwatch();	

	$startButton.on('click', function(){
		$(this).fadeOut();
		$pauseButton.fadeIn().addClass('active');
		timer.start();
		$stopButton.css('display', 'block').hide().fadeIn();
	});

	$pauseButton.on('click', function() {
		if($(this).hasClass('active')) {
			$(this).find('span').html('RESUME');
			$(this).removeClass('active');
			timer.stop();
		} else {
			$(this).find('span').html('PAUSE');
			$(this).addClass('active');
			timer.start();
		}
	});

	$stopButton.on('click', function() {
		timer.stop();
		timer.reset();
		$pauseButton.fadeOut();
		$startButton.fadeIn();
		$submitForm.fadeIn();
		$(this).fadeOut();
	});

	$submitButton.on('click', function() {
		$submitForm.fadeOut();
	});

});







var Stopwatch = function() {

	var startButton = $('#startButton');
	var pauseButton = $('#pauseButton');
	var stopButton = $('#stopButton');
	var timer = $('#currentTime');

	var offset, clock, interval;
	var hour,minute,second;
	var output;

  // default options
  options = {};
  options.delay = 100;



  // initialize
  reset();

  // private functions
  function createTimer() {
    return document.createElement("span");
  }


  function start() {
    if (!interval) {
      offset   = Date.now();
      interval = setInterval(update, options.delay);
    }
  }

  function stop() {
    if (interval) {
      clearInterval(interval);
      interval = null;
    }
  }

  function reset() {
    clock = 0;
    render();
  }

  function update() {
    clock += delta();
    render();
  }

  function render() {
  	seconds = clock/1000;
  	second = Math.round(seconds%60);
  	minute = Math.round((seconds/60)%60);
  	hour = Math.round((seconds/3600)%24);
  	/* 
  	seconds = modulo 60
  	minutes = /60 modulo 24
  	hours = modulo
  	*/
  	output = populate(hour) + ':' + populate(minute) + ':' + populate(second);
    timer.html(output); 
  }

  function populate(number) {
  	res = (number < 10 ? '0' : '') + number
  	return res;
  }

  function delta() {
    var now = Date.now(),
        d   = now - offset;
    offset = now;
    return d;
  }

  // public API
  this.start  = start;
  this.stop   = stop;
  this.reset  = reset;
};



