/*-- +-Button Start------------------------------>*/

$('#adds').click(function add() {
    var $rooms = $("#noOfRoom");
    var a = $rooms.val();

    a++;
    $("#subs").prop("disabled", !a);
    $rooms.val(a);

    $("#noOfRoom").trigger(isNegative());
});

$("#subs").prop("disabled", !$("#noOfRoom").val());

$('#subs').click(function subst() {
    var $rooms = $("#noOfRoom");
    var b = $rooms.val();
    if (b >= 2) {
        b--;
        $rooms.val(b);
    }
    else {
        
        $("#subs").prop("disabled", true);
    }
});
/*-- +-Button End--------------------------------->*/