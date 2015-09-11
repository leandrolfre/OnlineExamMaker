<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>

<head>
    <title>Online Test Exam Maker</title>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
</head>

<body>

    <c:set var="questionSet" value="${exam.getQuestionSet()}" />

    <h1>Welcome to Online Test Exam Maker</h1>
    <div id="elapsedTime"></div>
    <select id="questionSelect">
    <c:forEach var="question" items="${questionSet}">
        <option value="${question.getLabel()}">${question.getLabel()}</option>
    </c:forEach>
    </select>

    <form:form name="examForm" action="/onlineTestMaker/grade" method="post" >

        <c:forEach var="question" items="${questionSet}">

            <div id="question_${question.getLabel()}" style="display: none;">
                <span>${question.label} - ${question.description}</span>
                <br/>
                <br/>

                <c:forEach var="choice" items="${question.getChoiceSet()}">
                    <input type="radio" name="answer[${question.id}]" value="${choice.id}"> ${choice.description}
                    <br/>
                </c:forEach>
            </div>

        </c:forEach>
        <br/>
        <br/>
        <input type="submit" value="finish">

    </form:form>

<script type="application/javascript">
    var startTime = Number(${exam.durationInMinute})*60;
    $(function(){
        toggleQuestions();
        $("#questionSelect").on("change",function(){
            toggleQuestions();
        });
        $("#elapsedTime").html(getFormattedTime(startTime));
        setInterval(updateElapsedTime, 1000);
        $.ajax({
            url : 'startTime.html'
        });

        setInterval(checkTime, 2000);

        $("input:submit").on("click",function(e) {
            e.preventDefault();
            var blankAnswer = false;
            $("div[id^='question_']").each(function(){
                if($(this).children("input:checked").length == 0) {
                    blankAnswer = true;
                }

            });

            if(blankAnswer) {
                if(confirm("There are blank answers . Do you want to continue?")) {
                    $("form[name='examForm']").submit();
                }
            } else {
                $("form[name='examForm']").submit();
            }

        });

    });

    function toggleQuestions() {
        var option = $("#questionSelect option:selected").val();
        $("div[id^='question_']").hide();
        $("#question_"+option).show();
    }

    function checkTime() {
        $.ajax({
            url : 'checkTime.html',
            success : function(data) {
                if (data == "true") {
                    alert("time is over");
                    $("form[name='examForm']").submit();
                }
            }
        });
    }

    function updateElapsedTime() {
        startTime-=1;

        $("#elapsedTime").html(getFormattedTime(startTime));

    }

    function getFormattedTime(time) {
        var h = parseInt(startTime/3600);
        var m = parseInt(startTime/60);
        var s = startTime%60;
        return h+":"+m+":"+s;
    }


</script>



</body>

</html>
