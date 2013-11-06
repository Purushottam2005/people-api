(function ($) {
    $.fn.jsonEditor = function () {
        return this.each(function () {
            function keyUpHandler() {
                var hasPreviouosError = self.next().hasClass("error");
                try {
                    JSON.parse(self.text())
                    if (hasPreviouosError) {
                        self.next().remove();
                    }
                } catch (e) {
                    if (hasPreviouosError) {
                        self.next().text(e.message);
                    } else {
                        self.after($("<code></code>").addClass("error").text(e.message))
                    }
                }
            }

            function formatJson() {
                try {
                    self.text(JSON.stringify(JSON.parse(self.text()), null, "  "))
                } catch (e) {
                }
            }
            var self = $(this).attr("contenteditable", true).on("keyup", keyUpHandler).on("blur", formatJson);
            formatJson();
        });
    }
    $(document).ready(function () {
        $("pre code.javascript").jsonEditor();
    })
})(jQuery)
