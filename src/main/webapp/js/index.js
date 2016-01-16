/* Video Overlay */
$('.video .overlay').on('click', function() {
  $(this).fadeOut(300);
});

/* Tabs */
$('.tab-content').not('.active').hide();

$('.tab-nav a').click(function(e) {
  e.preventDefault();
  $('.tab-nav a').removeClass('active');
  $(this).addClass('active');

  $('.tab-content').hide();
  $($.attr(this, 'href')).fadeIn(300);
});

/* Fixed Navigation */
if ($('header').offset().top > 0) {
  $('body').addClass('fixed-header');
} else {
  $('body').removeClass('fixed-header');
}

/* Scroll Function */
$(window).scroll(function() {
  /* Fixed Navigation */
  if ($('header').offset().top > 0) {
    $('body').addClass('fixed-header');
  } else {
    $('body').removeClass('fixed-header');
  }
});