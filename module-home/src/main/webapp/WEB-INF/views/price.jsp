<!-- 포워딩용 메인 페이지 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
  <meta name="author" content="INSPIRO"/>
  <meta name="description" content="Themeforest Template Polo">
  <!-- Document title -->
  <title>코딩 & 로봇 메이트</title>
  <link rel="shortcut icon" href="/homejjang.ico">

  <!-- Stylesheets & Fonts -->
  <link href="/assets/css/plugins.css" rel="stylesheet">
  <link href="/assets/css/style.css" rel="stylesheet">

  <!-- Global site tag (gtag.js) - Google Analytics -->
  <script async src="https://www.googletagmanager.com/gtag/js?id=G-68Y1BSKSW1"></script>
  <script>
      window.dataLayer = window.dataLayer || [];

      function gtag() {
          dataLayer.push(arguments);
      }

      gtag('js', new Date());

      gtag('config', 'G-68Y1BSKSW1');
  </script>
  <script>
      // if (document.location.protocol == 'http:') {
      //     document.location.href = document.location.href.replace('http:', 'https:');
      // }

  </script>
</head>
<body>
<body>
<!-- Body Inner -->
<div class="body-inner">
  <!-- Header -->
  <!-- Topbar -->

  <!-- end: Topbar -->
  <jsp:include page="header.jsp" flush="false"/>
  <!-- Page title -->
  <section id="page-title" class="text-light" data-bg-parallax="" style="padding:0 !important;">
    <div class="" style="">
      <!-- portfolio item -->
      <div class="portfolio-item img-zoom">
        <div class="portfolio-item-wrap">
          <div class="portfolio-image">
            <a href="#"><img src="images/gallery/main.png" alt=""/></a>
          </div>
          <div class="portfolio-description">
            <a href="portfolio-page-grid-gallery.html">
              <h3>카이스트 주관 2020 AI축구 고등부</h3>
              <span>장관상 수상</span>
            </a>
          </div>
        </div>
      </div>
      <!-- end: portfolio item -->


      <div class="page-title">
        <h1></h1>
      </div>

    </div>
  </section>
  <!-- end: Page title -->
  <!-- Content -->
  <section id="page-content">
    <div class="container">
      <!-- Pricing Table -->
      <div class="heading-text heading-line text-center pb-5">
        <h4>Default pricing table</h4>
      </div>
      <div class="row pricing-table">
        <div class="col-lg-4 col-md-12 col-12">
          <div class="plan">
            <div class="plan-header">
              <h4>Ultimate Plan</h4>
              <p class="text-muted">Plan short description</p>
              <div class="plan-price"><sup>$</sup>80<span>/mo</span> </div>
              <div class="countdown small" data-countdown="2019/12/19 11:34:51"></div>
            </div>
            <div class="plan-list">
              <ul>
                <li><i class="fas fa-globe-americas"></i>Unlimited Websites</li>
                <li><i class="fa fa-thumbs-up"></i>Unlimited Storage</li>
                <li><i class="fa fa-signal"></i>Unlimited Bandwidth</li>
                <li><i class="fa fa-user"></i>1000 Email Addresses</li>
                <li><i class="fa fa-star"></i>Free domain with annual plan</li>
                <li><i class="fa fa-rocket"></i>4X Processing Power</li>
                <li><i class="fa fa-server"></i>Premium DNS</li>
              </ul>
              <div class="plan-button">
                <a href="#" class="btn btn-light">Buy Now</a>
              </div>
            </div>
          </div>
        </div>
        <div class="col-lg-4 col-md-12 col-12">
          <div class="plan featured">
            <div class="plan-header">
              <h4>Deluxe Plan</h4>
              <p class="text-muted">Plan short description</p>
              <div class="plan-price"><sup>$</sup>20<span>/mo</span> </div>
              <div class="countdown small" data-countdown="2019/08/11 11:34:51"></div>
            </div>
            <div class="plan-list">
              <ul>
                <li><i class="fas fa-globe-americas"></i>Unlimited Websites</li>
                <li><i class="fa fa-thumbs-up"></i>Unlimited Storage</li>
                <li><i class="fa fa-signal"></i>Unlimited Bandwidth</li>
                <li><i class="fa fa-user"></i>1000 Email Addresses</li>
                <li><i class="fa fa-star"></i>Free domain with annual plan</li>
                <li><i class="fa fa-rocket"></i>4X Processing Power</li>
                <li><i class="fa fa-server"></i>Premium DNS</li>
              </ul>
              <div class="plan-button">
                <a href="#" class="btn">Buy Now</a>
              </div>
            </div>
          </div>
        </div>
        <div class="col-lg-4 col-md-12 col-12">
          <div class="plan">
            <div class="plan-header">
              <h4>Professional Plan</h4>
              <p class="text-muted">Plan short description</p>
              <div class="plan-price"><sup>$</sup>69<span>/mo</span> </div>
              <div class="countdown small" data-countdown="2019/11/15 11:34:51"></div>
            </div>
            <div class="plan-list">
              <ul>
                <li><i class="fas fa-globe-americas"></i>Unlimited Websites</li>
                <li><i class="fa fa-thumbs-up"></i>Unlimited Storage</li>
                <li><i class="fa fa-signal"></i>Unlimited Bandwidth</li>
                <li><i class="fa fa-user"></i>1000 Email Addresses</li>
                <li><i class="fa fa-star"></i>Free domain with annual plan</li>
                <li><i class="fa fa-rocket"></i>4X Processing Power</li>
                <li><i class="fa fa-server"></i>Premium DNS</li>
              </ul>
              <div class="plan-button">
                <a href="#" class="btn btn-light">Buy Now</a>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- end: Pricing Table -->
    </div>
  </section> <!-- end: Content -->
  <jsp:include page="footer.jsp" flush="false"/>
</div>
<!-- end: Body Inner -->
<!-- Scroll top -->
<a id="scrollTop"><i class="icon-chevron-up"></i><i class="icon-chevron-up"></i></a>
<!--Plugins-->
<script src="assets/js/jquery.js"></script>
<script src="assets/js/plugins.js"></script>
<!--Template functions-->
<script src="assets/js/functions.js"></script>
<!--Google Maps files-->
<script type='text/javascript'
        src='//maps.googleapis.com/maps/api/js?key=AIzaSyAZIus-_huNW25Jl7RPmHgoGZjD5udgBMI'></script>
<script type="text/javascript">
</script>
<!--google Maps end-->
</body>

</html>
