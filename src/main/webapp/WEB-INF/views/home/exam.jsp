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

    <select id="questionSelect">
    <c:forEach var="question" items="${questionSet}">
        <option value="${question.getLabel()}">${question.getLabel()}</option>
    </c:forEach>
    </select>

    <form:form action="/grade" method="post" >

        <c:forEach var="question" items="${questionSet}">

            <div id="question_${question.getLabel()}" style="display: none;">
                <span>${question.label} - ${question.description}</span>
                <br/>
                <br/>

                <c:forEach var="choice" items="${question.getChoiceSet()}">
                    <input type="radio" name="answer[${question.getId()}]"> ${choice.description}
                    <br/>
                </c:forEach>
            </div>

        </c:forEach>

    </form:form>

<script type="application/javascript">
    $(function(){
        toggleQuestions();
        $("#questionSelect").on("change",function(){
            toggleQuestions();
        });
    });

    function toggleQuestions() {
        var option = $("#questionSelect option:selected").val();
        $("div[id^='question_']").hide();
        $("#question_"+option).show();
    }


</script>



</body>

</html>
