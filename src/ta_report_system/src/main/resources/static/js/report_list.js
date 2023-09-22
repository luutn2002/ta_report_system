var start = document.getElementById("startTime").value;
var end = document.getElementById("endTime").value;
var breakHour = document.getElementById("breakHour").value;
var breakMinute = document.getElementById("breakMinute").value;

document.getElementById("startTime").onchange = function() {diff(start, end, breakHour, breakMinute)};
document.getElementById("endTime").onchange = function() {diff(start, end, breakHour, breakMinute)};
document.getElementById("breakHour").onchange = function() {diff(start, end, breakHour, breakMinute)};
document.getElementById("breakMinute").onchange = function() {diff(start, end, breakHour, breakMinute)};


$('#dayWorkLimitAlert').hide();
$('#nightWorkAlert').hide();
$('#startEndNotMatchAlert').hide();
$('#lowWorkingTimeAlert').hide();
$('#lowRestAlert').hide();


function diff(start, end, breakHour, breakMinute) {

    const CONSECUTIVE_WORKING_LIMIT = 6*1000*60*60;
    const DAY_WORKING_LIMIT = 8*1000*60*60;
    const MINIMUM_BREAK_LIMIT = 1000*60*60;

    start = document.getElementById("startTime").value; //to update time value in each input bar
    end = document.getElementById("endTime").value; //to update time value in each input bar
    breakHour = document.getElementById("breakHour").value;
    breakMinute = document.getElementById("breakMinute").value;
    
    
    start = start.split(":");
    end = end.split(":");
    var startDate = new Date(0, 0, 0, start[0], start[1], 0);
    var endDate = new Date(0, 0, 0, end[0], end[1], 0);

    var startNight = new Date(0, 0, 0, 22, 0, 0);
    var endNight = new Date(0, 0, 0, 5, 0, 0);

    document.getElementById("createButton").disabled = true;

    if ((startDate > startNight || startDate < endNight) || (endDate > startNight)){
        $('#nightWorkAlert').show();
        return "00:00";
    }else if (startDate < endDate){
        $('#nightWorkAlert').hide();
        $('#startEndNotMatchAlert').hide();
        var diff = endDate.getTime() - startDate.getTime();
        var breakTime = breakHour*1000*60*60 + breakMinute*1000*60;

        if(diff-breakTime > DAY_WORKING_LIMIT){
            $('#dayWorkLimitAlert').show();
            return "00:00";
        } else if (diff >= CONSECUTIVE_WORKING_LIMIT && breakTime < MINIMUM_BREAK_LIMIT){
            $('#dayWorkLimitAlert').hide();
            $('#lowRestAlert').show();
            return "00:00";
        } else {
            $('#dayWorkLimitAlert').hide();
            $('#lowRestAlert').hide();
        }

        diff -= breakTime;
        var hours = Math.floor(diff/1000/60/60);
        diff -= hours * 1000 * 60 * 60;
        var minutes = Math.floor(diff / 1000 / 60);
            if(hours < 1){
                $('#lowWorkingTimeAlert').show();
                return "00:00";
            } else{ 
                $('#lowWorkingTimeAlert').hide();
                document.getElementById("createButton").disabled = false;
                return (hours < 9 ? "0" : "") + hours + ":" + (minutes < 9 ? "0" : "") + minutes;
            }
    } else{
        $('#nightWorkAlert').hide();
        $('#startEndNotMatchAlert').show();
        return "00:00";
    }

}

function getMinutesFromString(){
    displayValue = document.getElementById("totalWorkHourDisplay").value;
    displayTime = displayValue.split(':');
    return parseInt(displayTime[0])*60 + parseInt(displayTime[1]);
}

function getMinutesFromBreakTime(breakHour, breakMinute){
    breakHour = document.getElementById("breakHour").value;
    breakMinute = document.getElementById("breakMinute").value;
    return parseInt(breakHour*60) + parseInt(breakMinute);
}


setInterval(function(){
    document.getElementById("totalWorkHourDisplay").value = diff(start, end, breakHour, breakMinute);
    document.getElementById("totalWorkHourSend").value = getMinutesFromString();
    document.getElementById("breakTimeSend").value = getMinutesFromBreakTime(breakHour, breakMinute);
}, 500);

//Block enter key to prevent accidental submission
$(document).ready(function() {
    $(window).keydown(function(event){
        if(event.keyCode == 13) {
            event.preventDefault();
            return false;
        }
    });
});