jQuery(function() {
	$('a:not([onclick])').each(function() {
		var foo = $(this).attr('href');
		$(this).click(function(){eval(foo);});
		$(this).attr('href','javascript:void(0)');
	});
	$('form[action]').each(function() {
		$(this).attr('action',$(this).attr('action') + '.action');
	});
});

function x(action) {
	$('<form id="_df" method="post"/>').attr('action',action + '.action').appendTo('body');
	arguments.length>1 && $('<input type="hidden" id="_id" name="id"/>').attr('value',arguments[1]).appendTo('#_df');
	$('#_df').submit();
}
