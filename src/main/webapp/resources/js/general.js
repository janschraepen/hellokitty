/* jQuery tabs */
$(function() {
    $('#tabs').tabs();
});

/* _event button */
$( document ).ready(function() {

    $('input[type="button"]').click(function (e) {
        e.preventDefault();

        var action = $(this).attr('action');
        $('input[name="_event"]').val(action);
        $(this).closest('form').submit();
    });

});

