<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>애니타임</title>
  <link href="css/login.css" type="text/css" rel="stylesheet">
  <meta charset="utf-8">
  <meta name="referrer" content="origin">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta property="og:type" content="website">
  <meta property="og:image" content="https://everytime.kr/images/og_image.png">
  <meta property="og:url" content="https://everytime.kr">
  <meta property="og:site_name" content="애니타임">
  <meta property="og:title" content="애니타임">
  <meta property="og:description" content="전국 400개 대학을 지원하는 대학교 커뮤니티 및 시간표 서비스. 시간표 작성 및 학업 관리, 학교 생활 정보, 학교별 커뮤니티 기능을 제공합니다.">
  <meta name="description" content="전국 400개 대학을 지원하는 대학교 커뮤니티 및 시간표 서비스. 시간표 작성 및 학업 관리, 학교 생활 정보, 학교별 커뮤니티 기능을 제공합니다.">
  <meta name="keywords" content="애니타임, 애타, everytime, 시간표, 수강신청, 대학생, 대학교, 대학, 대학생 시간표, 대학교 시간표, 대학생 커뮤니티, 대학교 커뮤니티">
  <script type="text/javascript" src="/js/extensions.jquery-1.10.2.min.js"></script>
  <script type="text/javascript" src="/js/about.js"></script>
</head>
<body>
  <aside>
    <div class="login">
      <a href="/" class="logo"><img src="image/logo.png" alt="애니타임" width="50px" height="80px"></a>
      <a href="/login" class="button login">로그인</a>
      <a href="/register" class="button register">애니타임 회원가입</a>
      <p class="find">
        <a href="/forgot">아이디/비밀번호 찾기</a>
      </p>
      <hr>
    </div>
    <form class="search">
      <p>우리 학교 커뮤니티 둘러보기</p>
      <input type="text" name="name" placeholder="찾으시는 캠퍼스를 검색하세요." autocomplete="off">
    </form>
    <div class="campuslist">
      <a href="https://khu.everytime.kr"><span class="name">한국대</span><span class="count">1명</span></a>
      <a href="https://cau.everytime.kr"><span class="name">우니대</span><span class="count">20,000명</span></a>
      <a href="https://yonsei.everytime.kr"><span class="name">옥이대</span><span class="count">3명</span></a>
    </div>
  </aside>
  <section class="community">
    <h2><strong>재학생 커뮤니티 애니타임</strong></h2>
    <div class="paragraph">
      <p>학교 인증을 거친 재학생들의 안전한 대화를 위한 <strong>익명 시스템</strong>과<br>학생들이 직접 게시판을 개설하여 운영하는 <strong>커뮤니티 플랫폼</strong>을 통해<br>많은 대학교에서 가장 활발히 이용하는 재학생 커뮤니티로 자리잡았습니다.</p>
    </div>
    <div class="figures">
      <div>
        <p class="icon"><img src="image/icon.authorized.png"></p>
        <p class="description">철저한 학교 인증</p>
      </div>
      <div>
        <p class="icon"><img src="image/icon.anonymous.png"></p>
        <p class="description">완벽한 익명 시스템</p>
      </div>
      <div>
        <p class="icon"><img src="image/icon.users.png"></p>
        <p class="description">재학생 운영 게시판</p>
      </div>
    </div>
  </section>
  <section class="footer white">
    <ul class="links">
      <li><a href="/page/serviceagreement">이용약관</a></li>
      <li class="privacy"><a href="/page/privacy">개인정보처리방침</a></li>
      <li><a href="/page/faq">문의하기</a></li>
      <li class="copyright"><a href="/">© 애니타임</a></li>
    </ul>
  </section>
  
  <script async="" src="https://www.googletagmanager.com/gtag/js?id=G-85ZNEFVRGL"></script>
  <script>
    window.dataLayer = window.dataLayer || [];
    function gtag(){dataLayer.push(arguments);}
    gtag('js', new Date());
    gtag('config', 'G-85ZNEFVRGL', { 'send_page_view': false });
    gtag('config', 'UA-22022140-4');
    gtag('event', 'page_view', {
      page_title: location.pathname,
      page_path: location.pathname + location.search,
      page_location: location.origin + location.pathname + location.search
    });
  </script>
</body>
</html>