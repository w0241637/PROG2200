
// https://api.jquery.com/jquery.ajax/
// The include script "jquery.min.js" pulls in this API

// .then explained... https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Promise/then

$(document).ready(function() {
    $.ajax({
        dataType: 'json',
        url: "http://localhost:8081/greeting"
    }).then(function(data) {
        $('.greeting-id').append(data.id);
        $('.greeting-content').append(data.content);
    });
    $('.greeting-test').append('...We ran hello.js');
});