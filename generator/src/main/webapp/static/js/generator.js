(function ($) {
    var navStack = [];

    function populateRel(event) {
        var method = $("#method").val();
        var dataObject;
        eval("dataObject=" + ($("#inputData").val().length ? $("#inputData").val() : "{}"));
        var data = "GET" == method ? $.param(dataObject) : JSON.stringify(dataObject);
        var contentType = "GET" == method ? "application/x-www-form-urlencoded; charset=UTF-8" : "application/json; charset=UTF-8";
        $.ajax({
            dataType: "json",
            url: $("#inputUrl").val(),
            type: method,
            data: data,
            contentType: contentType,
            success: function (data) {
                if(!data){
                    return;
                }

                var apiNav = $("#apiNav");
                apiNav.find("li.relNav").remove();
                data.links.forEach(function (link) {
                    apiNav.append(createLink(link));
                })
                $("#output").text(JSON.stringify(data, null, "  "));
            },
            error:function(error){
                alert(error.status + " " + error.statusText);
            }
        });
        event.preventDefault();
    }

    function createLink(link) {
        return $("<li />").addClass("relNav").append($("<a />").attr("href", link.href).text(link.rel));
    }

    function navButtonClicked(event) {
        var $inputUrl = $("#inputUrl");
        var oldUrl = $inputUrl.val();

        navStack.push(oldUrl);
        setBack(oldUrl)

        $inputUrl.val($(this).attr("href")).trigger("change");
        event.preventDefault();
    }

    function backButtonClicked(event) {
        var oldUrl = navStack.pop();
        if (!navStack.length) {
            navStack.push(oldUrl);
        }
        setBack(oldUrl)

        $("#inputUrl").val(oldUrl).trigger("change");
        event.preventDefault();
    }

    function setBack(url) {
        $("li.back a").attr("href", url);
    }

    $(function () {
        navStack.push($("#inputUrl").val());
        setBack($("#inputUrl").val());
        $("#apiNav").on("click", "li.relNav a", navButtonClicked);
        $("#apiNav").on("click", "li.back a", backButtonClicked);
        $("#apiNav").on("click", "li.refresh a", populateRel);
        $("#inputUrl").on("change", populateRel).trigger("change");
        $("#method").on("change", populateRel);
    });

})(jQuery);