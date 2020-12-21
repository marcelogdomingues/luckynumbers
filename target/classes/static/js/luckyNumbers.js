function insertCoin(machineName) {
    $.get(machineName + '/luckynumbers/insert-coin').done(function (fragment) {
        // get from controller
        $('#controls').replaceWith(fragment); // update snippet of page
    });
}

function play(machineName) {
    $.get(machineName + '/luckynumbers/play').done(function (fragment) {
        // get from controller
        $('#slot-machine').replaceWith(fragment); // update snippet of page
    });
}