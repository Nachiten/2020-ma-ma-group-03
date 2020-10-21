$(document).ready(function(){
	$('ul.link li a:first').addClass('active');
	$('.sections article').hide();
	$('.sections article:first').show();

	$('ul.link li a').click(function(){
		$('ul.link li a').removeClass('active');
		$(this).addClass('active');
		$('.sections article').hide();

		var activeTab = $(this).attr('href');
		$(activeTab).show();
		return false;
	});
});