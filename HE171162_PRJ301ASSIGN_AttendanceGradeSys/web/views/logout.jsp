

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logged out</title>
        <link rel="stylesheet" href="css/dist/index.css" />
    </head>
    <body class="text-center">
        <h1 class="font-bold text-2xl mt-2">Logout successful!</h1>
        <br/>
        <p class="text-lg"> You will be redirected to the same page after <span class="font-bold text-xl text-red-500" id="time"></span> seconds... </p>
        <script> 
        var count = 2;
        var time = document.getElementById('time');
        time.innerHTML = count;
        function counting()
        {
            count --;
            time.innerHTML = count;
            if(count <= 0 )
            {
                window.location.href = "${requestScope.target}";
            }
        }
        setInterval(counting,1000);
        
        </script>
    </body>
</html>
