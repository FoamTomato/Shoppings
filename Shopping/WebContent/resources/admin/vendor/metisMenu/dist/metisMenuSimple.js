/**
 * 
 *//**
 * Created by hughd on 2017/8/9.
 * 简单版 - v1.0
 * 仅实现基础折叠
 */
;(function ($, window, document, undefined) {
    var pluginName = "metisMenu";
    var toggle = true;

    function Plugin(element, options) {
        this.element = $(element);
        this.init();
    }

    Plugin.prototype = {
        init: function () {
            var $this = this.element;
            // var obj = this;
            $this.find("li.active").has("ul").children("ul").addClass('collapse in');// 初始激活的元素的后代全部展开
            $this.find('li').not(".active").has("ul").children("ul").addClass('collapse');// 未激活的元素显示隐藏
            // 监听点击事件
            $this.find("li").children("a").on("click." + pluginName, function () {
                //被点击的元素激活样式并展开
                $(this).parent('li').toggleClass('active').children("ul").collapse("toggle");
                if (toggle) {
                    //将其他被展开的元素闭合
                    $(this).parent("li").siblings().removeClass("active").children("ul").collapse("hide");
                }
            });
        }
    }
    $.fn[pluginName] = function (options) {
        // 实例化Plugin对象,并存放在元素<ul#side-menu>._proto_.metisMenu中
        this.data(pluginName, new Plugin(this, options));
        return this;
    }
})(jQuery, window, document);