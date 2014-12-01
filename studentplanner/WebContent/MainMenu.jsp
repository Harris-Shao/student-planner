<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
    <script src="js/daypilot-all.min.js" type="text/javascript"></script>
    <script src="js/daypilot-modal-2.1.js" type="text/javascript"></script> 
    <link type="text/css" rel="stylesheet" href="css/calendar_green.css" />
    <link type="text/css" rel="stylesheet" href="css/calendar_transparent.css" />  
    <link type="text/css" rel="stylesheet" href="css/calendar_white.css" />     
</head>
<body>
    <div class="space">
        CSS Theme:
        <select id="theme">
            <option value="calendar_default">Default</option>
            <option value="calendar_green">Green</option>
            <option value="calendar_transparent">Transparent</option>
            <option value="calendar_white">White</option>
        </select>
    </div>

    <div style="float:left; width: 160px;">
        <div id="nav"></div>
    </div>
    <div style="margin-left: 160px;">
        <div id="dp"></div>
    </div>

   <!--<script type="text/javascript">
    function editEvent(e) {
    var modal = new DayPilot.Modal();
    modal.top = 60; // position of the dialog top (y), relative to the visible area
    modal.width = 300; // width of the dialog
    modal.height = 250; // height of the dialog
    modal.opacity = 70; // opacity of the background
    modal.border = "10px solid #d0d0d0";  // dialog box border
    modal.closed = function() { // callback executed after the dialog is closed
    if(this.result == "OK") {  // if the
    dp.commandCallBack('refresh');
    }
    };
    modal.showUrl("Edit.aspx?id=" + e.value());
    }
    </script>--> 


    <script type="text/javascript">

        //MONTHLY NAVIGATOR

    var nav = new DayPilot.Navigator("nav");
    nav.showMonths = 3;
    nav.skipMonths = 3;
    nav.selectMode = "week";
    nav.init();

    </script>

    
    <script type="text/javascript">

        //calendar creation

        var dp = new DayPilot.Calendar("dp");
        dp.viewType = "Week";

        
        dp.init();
        
        dp.contextMenu = new DayPilot.Menu({
            items: [
            { text: "Share", onclick: function () { alert("Share with: ");} },
            { text: "Show event ID", onclick: function () { alert("Event value: " + this.source.value()); } },
            { text: "Show event text", onclick: function () { alert("Event text: " + this.source.text()); } },
            { text: "Show event start", onclick: function () { alert("Event start: " + this.source.start().toStringSortable()); } },
            { text: "Delete", onclick: function () { dp.events.remove(this.source); } }
            ]
        });


        // On-click; use drag to stretch event. Can move event around.

        dp.onTimeRangeSelected = function (args) {     
            var name = prompt("New event name:", "Event");
            dp.clearSelection();
            if (!name) return;
            var e = new DayPilot.Event({
                start: args.start,
                end: args.end,
                id: DayPilot.guid(),
                resource: args.resource,
                text: name
            });
            dp.events.add(e);
         
            dp.message("Created");
        };

    </script>

   
   

    <!--nav.onTimeRangeSelected = function(args) {
    dp.startDate = args.day;
    dp.update();
    loadEvents();
    };-->


    <script type="text/javascript">

        // CSS THEME Choose

    $(document).ready(function ($) {
        $("#theme").change(function (e) {
            dp.theme = this.value;
            dp.update();
        });
    });
    </script>

    <script type="text/javascript">

        // NEW EVENT

    var e = new DayPilot.Event({start:new DayPilot.Date(), end:(new DayPilot.Date()).addHours(5), value: DayPilot.guid(), text: "Test Event", resource:'E'});
    dp.events.add(e);
        </script>



    </body>
    </html>
