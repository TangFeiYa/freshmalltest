function bsStep(i) {
	$('.step').each(function() {
		var a, $this = $(this);
		if(i > $this.find('li').length) {
			a = $this.find('li').length;
		} else if(i == undefined && $this.data('step') == undefined) {
			a = 1
		} else if(i == undefined && $this.data('step') != undefined) {
			a = $(this).data('step');
		} else {
			a = i
		}
		$(this).find('li').removeClass('active');
		$(this).find('li:lt(' + a + ')').addClass('active');
	})
};