/**
 * Created by ke.gong on 2017/1/28.
 */
jQuery(document).ready(function () {

    $("#submitbutton").on("click", function () {
        ajaxSubmit("./trendQuery", $('#searchForm').serialize(), function(data){
            plotLine(data);
            fillTable(data);
        });
    });

    $(function () {
        fillDate(-30, 0);
        focusMenu();
        $('#submitbutton').trigger("click");
    });

});