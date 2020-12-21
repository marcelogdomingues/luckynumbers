function insertCoin(machineName) {
    $.get(machineName+'/multipleluckynumbers/insert-coin').done(function (fragment) {
        // get from controller
        $('#controls').replaceWith(fragment); // update snippet of page
    });
}
function play(machineName) {
    $.get(machineName+'/multipleluckynumbers/play').done(function (fragment) {
        // get from controller
        $('#slot-machine').replaceWith(fragment); // update snippet of page
    });
}
