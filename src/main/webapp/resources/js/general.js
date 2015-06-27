// jQuery tabs
$(function() {
    var activeTab = $('#tabs').attr('active-tab');
    $('#tabs').tabs({active: activeTab});
});


$(document).ready(function() {

    // prevent form submission on ENTER/RETURN
    $('input').keydown(function(event){
        if(event.keyCode == 13) {
            event.preventDefault();
            return false;
        }
    });

    // _event button
    $('input[type="button"]').click(function(e) {
        e.preventDefault();

        var action = $(this).attr('action');
        $('input[name="_event"]').val(action);
        $(this).closest('form').submit();
    });

    // open contactCard
    $('.open').click(function(e) {
        $(this).nextAll('.contactCard').show();
        $(this).nextAll('.close').show();
        $(this).hide();
    });

    // close contactCard
    $('.close').click(function(e) {
        $(this).nextAll('.contactCard').hide();
        $(this).prevAll('.open').show();
        $(this).hide();
    });

    // age culculation
    $('form input[name="age"]').focusout(function(e) {
        var birthYear = parseInt($(this).val());
        var currentYear = new Date().getFullYear();
        var currentAge = currentYear - birthYear;
        if (currentAge >= 0) {
            $('div.currentAge').html(currentAge);
        } else {
            $('div.currentAge').html('N/A');
        }
    });

});

