
// https://api.jquery.com/jquery.ajax/
// The include script "jquery.min.js" pulls in this API
//

$(document).ready(function() {     // https://api.jquery.com/ready/#entry-examples

    $.getJSON( "http://localhost:8081/greeting", function( data ) {
        $.each( data, function( key, val ) {
            console.log(" key= " + key + "  val= " + val );
        });
        $('.greeting-id').append(data.id);
        $('.greeting-content').append(data.content);

    });

});

