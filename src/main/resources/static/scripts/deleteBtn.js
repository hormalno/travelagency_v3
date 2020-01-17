$(function() {
    $("#deleteBtn").popover({
        html: true,
        title: " confirm",
        placement: 'top',
        content: function () {
            return $("#popover-content").html()
        }
    });

})