/* jQuery tabs */
$(function() {
    var activeTab = $('#tabs').attr('active-tab');
    $('#tabs').tabs({active: activeTab});
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

