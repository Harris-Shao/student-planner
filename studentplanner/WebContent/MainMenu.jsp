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

    <!-- CSS THEME MENU -->


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

    <script type="text/javascript">

         $(document).ready(function ($) {
            $("#theme").change(function (e) {
                dp.theme = this.value;
                dp.update();
            });
        });
    </script>



    <script type="text/javascript">

        // MONTHLY NAVIGATOR

        var nav = new DayPilot.Navigator("nav");
        nav.showMonths = 3;
        nav.selectMode = "week";
        nav.onTimeRangeSelected = function (args) {
            dp.startDate = args.start;
            dp.update();
        };
        nav.init();

    </script>

    
    <script type="text/javascript">

        // CALENDAR CREATION

        var dp = new DayPilot.Calendar("dp");
        dp.viewType = "Week";
        dp.heightSpec = "Fixed";
        dp.height = 600;        
        dp.init();
        

        // RIGHT_CLICK MENU FOR EVENTS

        dp.contextMenu = new DayPilot.Menu({
            items: [
            { text: "Share", onclick: function () { var user = prompt("Share with: ", "Email Address"); if (!name) return;} },
            { text: "Show event ID", onclick: function () { alert("Event value: " + this.source.value()); } },
            { text: "Show event text", onclick: function () { alert("Event text: " + this.source.text()); } },
            { text: "Show event start", onclick: function () { alert("Event start: " + this.source.start().toStringSortable()); } },
            { text: "Show event stop", onclick: function () { alert("Event stop: " + this.source.end().toStringSortable()); } },
            { text: "Delete", onclick: function () { dp.events.remove(this.source); } }
            ]
        });
       

        // EVENT CREATION
        // on-click; use drag to stretch event. Can move event around.

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


    

    </body>
    </html>
