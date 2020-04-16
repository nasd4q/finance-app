function enterDate(date) {
    document.getElementsByTagName('input')[4].value=date.toLocaleString().substring(0,10);
    document.getElementsByTagName('input')[5].value=date.toLocaleString().substring(0,10);
}

function iterateOneDay(current) {
    current.setTime(current.getTime() + 86400000);
}


function setDateAndClickDownload() {
    year = %d;
    month = %d;
    day = %d;
    date = new Date(year, month-1, day, 0, 0, 0, 0);

    enterDate(date);

    while (document.getElementsByTagName('input')[14].checked==false) {
        document.getElementsByTagName('input')[14].click();
    }

    document.getElementsByTagName('select')[0].options[5].selected=true;

    setTimeout(function(){
        document.getElementsByTagName('input')[48].click();
}, 500);
}


setDateAndClickDownload();