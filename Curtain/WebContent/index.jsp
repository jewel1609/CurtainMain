<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<title>W3.CSS</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<body class="w3-light-grey">

<div class="w3-topnav w3-large w3-teal">
  <a href="#">my page</a>
</div>
<br>
<div class="w3-container w3-left">
<nav class="w3-sidenav w3-collapse w3-white w3-animate-left w3-card-2" style="z-index:3;width:250px;">
  <a href="#" class="w3-border-bottom w3-large"><img src="http://www.w3schools.com/images/w3schools.png" style="width:80%;"></a>
  <a href="javascript:void(0)" onclick="w3_close()" 
  class="w3-text-teal w3-hide-large w3-closenav w3-large">Close ×</a>
  <a href="#" class="w3-light-grey w3-medium">Home</a>		
  <a href="#">Typography</a>		
  <a href="#">Colors</a>		
  <a href="#">Utilities</a>		
  <a href="#">Containers</a>		
  <a href="#">Buttons</a>		
  <a href="#">Cards</a>		
  <a href="#">Alerts</a>		
  <div class="w3-accordion">
    <a onclick="myAccordion('demo')" href="javascript:void(0)">Accordions <i class="fa fa-caret-down"></i></a>
    <div id="demo" class="w3-accordion-content w3-animate-left w3-padding">
      <a href="#">Link 1</a>
      <a href="#">Link 2</a>
      <a href="#">Link 3</a>
    </div>
  </div>
  <a href="#">Tables</a>		
</nav>
</div>


<div class="w3-container w3-center">    
  <div class="w3-row">

    <div class="w3-col m7">
    
      <div class="w3-row-padding">
        <div class="w3-col m12">
          <div class="w3-card-2 w3-round w3-white">
            <div class="w3-container w3-padding w3-left-align">
              <p contenteditable="true">Status: Feeling Blue</p>
              <button type="button" class="w3-btn w3-teal">Like</button> 
            </div>
          </div>
        </div>
      </div>
      
      <div class="w3-row-padding w3-margin-top">
        <div class="w3-col m9">
          <div class="w3-card-2 w3-white w3-round-large">
            <div class="w3-container">
              <p>Just Forgot that I had to mention something about someone to someone about how I forgot something, but now I forgot it. Ahh, forget it! Or wait. I remember.... no I don't.</p>
            </div>
          </div>
        </div>
      </div>
      
      <div class="w3-row-padding w3-margin-top">
        <div class="w3-col m9">
          <div class="w3-card-2 w3-white w3-round-large">
            <div class="w3-container">
              <p>Just Forgot that I had to mention something about someone to someone about how I forgot something, but now I forgot it. Ahh, forget it! Or wait. I remember.... no I don't.</p>
            </div>
          </div>
        </div>
      </div>
      
      <div class="w3-row-padding w3-margin-top">
        <div class="w3-col m9">
          <div class="w3-card-2 w3-white w3-round-large">
            <div class="w3-container">
              <p>Just Forgot that I had to mention something about someone to someone about how I forgot something, but now I forgot it. Ahh, forget it! Or wait. I remember.... no I don't.</p>
            </div>
          </div>
        </div>
      </div>
      
      <div class="w3-row-padding w3-margin-top w3-margin-bottom">
        <div class="w3-col m9">
          <div class="w3-card-2 w3-white w3-round-large">
            <div class="w3-container">
              <p>Just Forgot that I had to mention something about someone to someone about how I forgot something, but now I forgot it. Ahh, forget it! Or wait. I remember.... no I don't.</p>
            </div>
          </div>
        </div>
      </div>     
    </div>
    
    <div class="w3-col m2">
      <div class="w3-card-2 w3-round w3-white">
        <div class="w3-container">
          <p>Upcoming Events:</p>
          <img src="img_5terre.png" alt="Cinque Terre" style="width:100%;">
          <p><strong>Italy</strong></p>
          <p>Friday 15:00</p>
          <p><button class="w3-btn">Info</button></p>
        </div>
      </div>
      <br>
      <div class="w3-card-2 w3-round w3-white w3-padding-16">
        <p>ADS</p>
      </div>
      <div class="w3-card-2 w3-round w3-white">
        <p>ADS</p>
      </div>
    </div>
    
  </div>
</div>

<br>

<footer class="w3-container w3-teal">
  <h5>Footer</h5>
  <p>Footer information goes here</p>
</footer>
     
</body>
</html> 