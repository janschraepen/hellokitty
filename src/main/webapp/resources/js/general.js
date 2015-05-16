/* _event button */
$( document ).ready(function() {

    $('input[type="button"]').click(function (e) {
        e.preventDefault();

        var action = $(this).attr('action');
        $('input[name="_event"]').val(action);
        $('form').submit();
    });

});

