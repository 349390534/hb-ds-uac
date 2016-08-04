var uuid="1302187b1aed8850146d79ce256c0f152bef4192";var IpPort = "webdatabj.chinaface.com:80";
var home = ("https:" == document.location.protocol ? "https://" : "http://")
    + IpPort + "/analytics/collector/1p.gif";
var fhtml = IpPort + "/analytics/www/frame.html";

var cookieid = "_cid";
var firstvisit = "_fdt";
var currentvisit = "_cdt";
var analytics_beginLoadMillTime = new Date().getTime();
var analytics_isSearchEngineer = false;

var _sbArray = "";
try {
    var _sbString = new String(window._sb);
} catch (err) {
    var _sbString = "";
}

var current = new Date();
var day = current.getDate();
var month = (current.getMonth() + 1);
var year = current.getFullYear();
var hours = current.getHours();
var minutes = current.getMinutes();
var seconds = current.getSeconds();

if (minutes < 10)
    minutes = "0" + minutes;

var dat = day + "/" + month + "/" + year + " " + hours + ":" + minutes + ":"
    + seconds;

var BrowserDetect = {

    init : function() {
        this.browser = this.searchString(this.dataBrowser)
            || "An unknown browser";
        this.version = this.searchVersion(navigator.userAgent)
            || this.searchVersion(navigator.appVersion)
            || "an unknown version";
        this.OS = this.searchString(this.dataOS) || "an unknown OS";
    },

    searchString : function(data) {
        for ( var i = 0; i < data.length; i++) {
            var dataString = data[i].string;
            var dataProp = data[i].prop;
            this.versionSearchString = data[i].versionSearch
                || data[i].identity;
            if (dataString) {
                if (dataString.indexOf(data[i].subString) != -1)
                    return data[i].identity;
            } else if (dataProp)
                return data[i].identity;
        }
    },

    searchVersion : function(dataString) {
        var index = dataString.indexOf(this.versionSearchString);
        if (index == -1)
            return;
        return parseFloat(dataString.substring(index
            + this.versionSearchString.length + 1));
    },

    dataBrowser : [ {
        string : navigator.userAgent,
        subString : "Chrome",
        identity : "Chrome"
    }, {
        string : navigator.userAgent,
        subString : "OmniWeb",
        versionSearch : "OmniWeb/",
        identity : "OmniWeb"
    }, {
        string : navigator.vendor,
        subString : "Apple",
        identity : "Safari",
        versionSearch : "Version"
    }, {
        prop : window.opera,
        identity : "Opera"
    }, {
        string : navigator.vendor,
        subString : "iCab",
        identity : "iCab"
    }, {
        string : navigator.vendor,
        subString : "KDE",
        identity : "Konqueror"
    }, {
        string : navigator.userAgent,
        subString : "Firefox",
        identity : "Firefox"
    }, {
        string : navigator.vendor,
        subString : "Camino",
        identity : "Camino"
    }, {
        string : navigator.userAgent,
        subString : "Netscape",
        identity : "Netscape"
    }, {
        string : navigator.userAgent,
        subString : "MSIE",
        identity : "Internet Explorer",
        versionSearch : "MSIE"
    }, {
        string : navigator.userAgent,
        subString : "Gecko",
        identity : "Mozilla",
        versionSearch : "rv"
    }, {
        string : navigator.userAgent,
        subString : "Mozilla",
        identity : "Netscape",
        versionSearch : "Mozilla"
    } ],

    dataOS : [ {
        string : navigator.platform,
        subString : "Win",
        identity : "Windows"
    }, {
        string : navigator.platform,
        subString : "Mac",
        identity : "Mac"
    }, {
        string : navigator.userAgent,
        subString : "iPhone",
        identity : "iPhone/iPod"
    }, {
        string : navigator.platform,
        subString : "Linux",
        identity : "Linux"
    } ]
};

BrowserDetect.init();

var Analytics = function(){

    var oThis = this;

    oThis.sendinfo = function() {
        var version = oThis.getFlashVersion().split(',').shift();

        var txt = "fdt=" + oThis.checkFirDate();
        txt += "&pdt=" + oThis.checkPreDate();
        txt += "&cdt=" + dat;
        txt += "&ctz=" + parseInt(current.getTimezoneOffset());
        txt += "&plf=" + encodeURIComponent(navigator.platform);
        txt += "&buo=" + encodeURIComponent(window.location.href);
        txt += "&uri=" + encodeURIComponent(window.location.pathname);
        txt += "&flv=" + version;

        if (navigator.javaEnabled() == true) {
            txt += "&jve=t";
        } else {
            txt += "&jve=f";
        }

        if (navigator.cookieEnabled == true) {
            txt += "&cke=t";
        } else {
            txt += "&cke=f";
        }

        txt += "&clr=" + screen.colorDepth;
        txt += "&res=" + screen.width + "x" + screen.height;
        txt += "&pld=" + (current.getTime() - analytics_beginLoadMillTime);
        txt += "&kwd=" + encodeURIComponent(oThis.getReferrerKeyword());
        txt += "&rul=" + encodeURIComponent(document.referrer);
        txt += "&rmd=" + encodeURIComponent(oThis.getRefferMode());
        txt += "&vid=" + oThis.checkCookie();
        txt += "&log=" + uuid;

        oThis.checkCurDate();

        if(_sbString != "") {
            _sbArray = _sbString.split(",");
            var i = 0;
            while (i<_sbArray.length) {
                txt+= "&" + encodeURIComponent(_sbArray[i]) + "=" + encodeURIComponent(_sbArray[i+1]);
                i=i+2;
            }
        }

        oThis.img(txt);
    },

        oThis.img = function(txt) {
            var image = new Image(1, 1);
            image.src = home + "?" + txt;
            image.onload = function(){
                image.onload = null;
            }
        },

        oThis.getCookie = function(c_name) {
            var i, x, y, ARRcookies = document.cookie.split(";");
            for (i = 0; i < ARRcookies.length; i++) {
                x = ARRcookies[i].substr(0, ARRcookies[i].indexOf("="));
                y = ARRcookies[i].substr(ARRcookies[i].indexOf("=") + 1);
                x = x.replace(/^\s+|\s+$/g, "");
                if (x == c_name) {
                    return unescape(y);
                }
            }
        },

        oThis.setCookie = function(c_name, value, exdays) {
            var exdate = new Date();
            exdate.setDate(exdate.getDate() + exdays);
            var c_value = escape(value)
                + ((exdays == null) ? "" : "; expires=" + exdate.toUTCString())
                + "; path=/";
            document.cookie = c_name + "=" + c_value;
        },

        oThis.checkCookie = function() {
            var uid = Analytics.getCookie(cookieid);
            if (uid != null && uid != "") {
                return uid;

            } else {
                if (uuid != null && uuid != "") {
                    Analytics.setCookie(cookieid, uuid, 365000);
                    return uuid;
                }
            }
        },

        oThis.checkFirDate = function() {
            var fir = Analytics.getCookie(firstvisit);
            if (fir != null && fir != "") {
                return fir;
            } else {
                Analytics.setCookie(firstvisit, dat, 365000);
                return dat;
            }
        },

        oThis.checkPreDate = function() {
            var cur = Analytics.getCookie(currentvisit);

            if (cur != null && cur != "") {
                return cur;
            } else {
                return "";
            }
        },

        oThis.checkCurDate = function() {
            Analytics.setCookie(currentvisit, dat, 365000);
        },

        oThis.getFlashVersion = function() {
            try {
                try {
                    var axo = new ActiveXObject('ShockwaveFlash.ShockwaveFlash.6');
                    try {
                        axo.AllowScriptAccess = 'always';
                    } catch (e) {
                        return '6,0,0';
                    }
                } catch (e) {
                }
                return new ActiveXObject('ShockwaveFlash.ShockwaveFlash')
                    .GetVariable('$version').replace(/\D+/g, ',').match(
                        /^,?(.+),?$/)[1];
            } catch (e) {
                try {
                    if (navigator.mimeTypes["application/x-shockwave-flash"].enabledPlugin) {
                        return (navigator.plugins["Shockwave Flash 2.0"] || navigator.plugins["Shockwave Flash"]).description
                            .replace(/\D+/g, ",").match(/^,?(.+),?$/)[1];
                    }
                } catch (e) {
                }
            }
            return '0,0,0';
        },

        oThis.getRefferMode = function() {
            var ref = document.referrer.split("?")[0];
            if (ref == "" || document.location.href.split("?")[0] == ref)
                return "D";
            else if (analytics_isSearchEngineer)
                return "O";
            else {
                return "R";
            }
        },

        oThis.getReferrerKeyword = function() {
            var keyWord = "";
            var fromUrl = document.referrer;
            var fromUrls = fromUrl.split("?");

            if (fromUrls[0].indexOf(".baidu.") != -1) {
                var baidu_wd = fromUrl.indexOf("wd=");
                if (baidu_wd != -1) {
                    var indexkey = fromUrl.indexOf("&", baidu_wd);
                    keyWord = fromUrl.substring(baidu_wd + 3, indexkey);
                }

            } else if (fromUrls[0].indexOf(".google.") != -1) {
                var google_q = fromUrl.indexOf("q=");
                if (google_q != -1) {
                    var indexkey = fromUrl.indexOf("&", google_q);
                    keyWord = fromUrl.substring(google_q + 2, indexkey);
                }

            } else if (fromUrls[0].indexOf(".sogou.") != -1) {
                var sogou_query = fromUrl.indexOf("query=");
                if (sogou_query != -1) {
                    var indexkey = fromUrl.indexOf("&", sogou_query);
                    keyWord = fromUrl.substring(sogou_query + 6, indexkey);
                }

            } else if (fromUrls[0].indexOf(".yahoo.") != -1) {
                var yahoo_p = fromUrl.indexOf("p=");
                if (yahoo_p != -1) {
                    var indexkey = fromUrl.indexOf("&", yahoo_p);
                    keyWord = fromUrl.substring(yahoo_p + 2, indexkey);
                }

            } else if (fromUrls[0].indexOf(".tom.") != -1) {
                var tom_word = fromUrl.indexOf("word=");
                if (tom_word != -1) {
                    var indexkey = fromUrl.indexOf("&", tom_word);
                    keyWord = fromUrl.substring(tom_word + 5, indexkey);
                }

            } else if (fromUrls[0].indexOf(".163.") != -1) {
                var q_163 = fromUrl.indexOf("q=");
                if (q_163 != -1) {
                    var indexkey = fromUrl.indexOf("&", q_163);
                    keyWord = fromUrl.substring(q_163 + 2, indexkey);
                }

            } else if (fromUrls[0].indexOf(".soso.") != -1) {
                var soso_w = fromUrl.indexOf("w=");
                if (soso_w != -1) {
                    var indexkey = fromUrl.indexOf("&", soso_w);
                    keyWord = fromUrl.substring(soso_w + 2, indexkey);
                }

            } else if (fromUrls[0].indexOf(".3721.") != -1) {
                var p_3721 = fromUrl.indexOf("p=");
                if (p_3721 != -1) {
                    var indexkey = fromUrl.indexOf("&", p_3721);
                    keyWord = fromUrl.substring(p_3721 + 2, indexkey);
                }

            } else if (fromUrls[0].indexOf(".yisou.") != -1) {
                var yisou_search = fromUrl.indexOf("search=");
                if (yisou_search != -1) {
                    var indexkey = fromUrl.indexOf("&", yisou_search);
                    keyWord = fromUrl.substring(yisou_search + 7, indexkey);
                }

            } else if (fromUrls[0].indexOf(".zhongsou.") != -1) {
                var zhongsou_w = fromUrl.indexOf("w=");
                if (zhongsou_w != -1) {
                    var indexkey = fromUrl.indexOf("&", zhongsou_w);
                    keyWord = fromUrl.substring(zhongsou_w + 2, indexkey);
                }

            } else if (fromUrls[0].indexOf(".live.") != -1) {
                var live_q = fromUrl.indexOf("q=");
                if (live_q != -1) {
                    var indexkey = fromUrl.indexOf("&", live_q);
                    keyWord = fromUrl.substring(live_q + 2, indexkey);
                }

            } else if (fromUrls[0].indexOf(".iask.") != -1) {
                var iask_k = fromUrl.indexOf("k=");
                if (iask_k != -1) {
                    var indexkey = fromUrl.indexOf("&", iask_k);
                    keyWord = fromUrl.substring(iask_k + 2, indexkey);
                }

            } else if (fromUrls[0].indexOf("openfind") != -1) {
                var openfind_q = fromUrl.indexOf("q=");
                if (openfind_q != -1) {
                    var indexkey = fromUrl.indexOf("&", openfind_q);
                    keyWord = fromUrl.substring(openfind_q + 2, indexkey);
                }

            } else if (fromUrls[0].indexOf(".alltheweb.") != -1) {
                var alltheweb_q = fromUrl.indexOf("q=");
                if (alltheweb_q != -1) {
                    var indexkey = fromUrl.indexOf("&", alltheweb_q);
                    keyWord = fromUrl.substring(alltheweb_q + 2, indexkey);
                }

            } else if (fromUrls[0].indexOf(".lycos.") != -1) {
                var lycos_query = fromUrl.indexOf("query=");
                if (lycos_query != -1) {
                    var indexkey = fromUrl.indexOf("&", lycos_query);
                    keyWord = fromUrl.substring(lycos_query + 6, indexkey);
                }

            } else if (fromUrls[0].indexOf(".onseek.") != -1) {
                var onseek_q = fromUrl.indexOf("q=");
                if (onseek_q != -1) {
                    var indexkey = fromUrl.indexOf("&", onseek_q);
                    keyWord = fromUrl.substring(onseek_q + 2, indexkey);
                }
            }

            if (keyWord != "")
                analytics_isSearchEngineer = true;

            return keyWord;
        }
};


var Analytics = new Analytics();

/**************************鹏程网首页,各模块统计 START  **************************/
(function(){
    try{
        var keys = (/http:\/\/www\.hna\.net\/WEBUI\/(\S+?)\/.*/i).exec(location.href),
            key = keys && keys.length ?keys[1]:null;
        if(!key || !jQuery){
            return;
        }
        var data={},
            blackConfig={
                //海航集团
                hnaportal:['#pageNavigationBar_ctl00','#scrollbox_Announcemen_JiTuan','#scrollbox_Announcemen','#pageLayout_ctl00_ctl12_ctl00_ctl02_ClientPagepartContainer','#pageLayout_ctl00_ctl13_ctl00_ctl02_ClientPagepartContainer','#pageLayout_ctl00_ctl16_ctl00_ctl02_ClientPagepartContainer','#pageLayout_ctl00_ctl14_ctl00_fragmentHolder_ctl00','#pageLayout_ctl00_ctl08_ctl00_fragmentHolder_ctl00','#pageLayout_ctl00_ctl11_ctl00_fragmentHolder_ctl00','#pageLayout_ctl00_ctl06_ctl00_fragmentHolder_ctl00_contentContainer','#pageLayout_ctl00_ctl07_ctl00_fragmentHolder_ctl00','#pageLayout_ctl00_ctl04_ctl00_fragmentHolder_ctl00','#pageLayout_ctl00_ctl01_ctl00_fragmentHolder_ctl00_labAD','#pageLayout_ctl00_ctl15_ctl00_fragmentHolder_ctl00'],
                //海南航空
                airportal:['#pageNavigationBar_ctl00','#scrollbox_Announcemen_JiTuan','#scrollbox_Announcemen','#pageLayout_ctl00_ctl12_ctl00_ctl02_ClientPagepartContainer','#pageLayout_ctl00_ctl15_ctl00_ctl02_ClientPagepartContainer','#pageLayout_ctl00_ctl16_ctl00_ctl02_ClientPagepartContainer','#pageLayout_ctl00_ctl13_ctl00_fragmentHolder_ctl00','#pageLayout_ctl00_ctl07_ctl00_fragmentHolder_ctl00','#pageLayout_ctl00_ctl10_ctl00_fragmentHolder_ctl00','#pageLayout_ctl00_ctl06_ctl00_fragmentHolder_ctl00_contentContainer','#pageLayout_ctl00_ctl08_ctl00_fragmentHolder_ctl00','#pageLayout_ctl00_ctl04_ctl00_fragmentHolder_ctl00','#pageLayout_ctl00_ctl01_ctl00_fragmentHolder_ctl00_labAD','#pageLayout_ctl00_ctl14_ctl00_fragmentHolder_ctl00'],
                //海航物流
                wlportal:['#pageNavigationBar_ctl00','#scrollbox_Announcemen_JiTuan','#scrollbox_Announcemen','#pageLayout_ctl00_ctl11_ctl00_ctl02_ClientPagepartContainer','#pageLayout_ctl00_ctl13_ctl00_ctl02_ClientPagepartContainer','#pageLayout_ctl00_ctl16_ctl00_ctl02_ClientPagepartContainer','#pageLayout_ctl00_ctl14_ctl00_fragmentHolder_ctl00','#pageLayout_ctl00_ctl07_ctl00_fragmentHolder_ctl00','#pageLayout_ctl00_ctl10_ctl00_fragmentHolder_ctl00','#pageLayout_ctl00_ctl04_ctl00_fragmentHolder_ctl00_contentContainer','#pageLayout_ctl00_ctl09_ctl00_fragmentHolder_ctl00','#pageLayout_ctl00_ctl05_ctl00_fragmentHolder_ctl00','#pageLayout_ctl00_ctl02_ctl00_fragmentHolder_ctl00_labAD','#pageLayout_ctl00_ctl15_ctl00_fragmentHolder_ctl00'],
                //海航资本
                industryportal:['#pageNavigationBar_ctl00','#scrollbox_Announcemen_JiTuan','#scrollbox_Announcemen','#pageLayout_ctl00_ctl08_ctl00_ctl02_ClientPagepartContainer','#pageLayout_ctl00_ctl12_ctl00_ctl02_ClientPagepartContainer','#pageLayout_ctl00_ctl14_ctl00_fragmentHolder_ctl00','#pageLayout_ctl00_ctl10_ctl00_fragmentHolder_ctl00','#pageLayout_ctl00_ctl11_ctl00_fragmentHolder_ctl00','#pageLayout_ctl00_ctl04_ctl00_fragmentHolder_ctl00_contentContainer','#pageLayout_ctl00_ctl09_ctl00_fragmentHolder_ctl00','#pageLayout_ctl00_ctl06_ctl00_fragmentHolder_ctl00','#pageLayout_ctl00_ctl03_ctl00_fragmentHolder_ctl00_labAD','#pageLayout_ctl00_ctl15_ctl00_fragmentHolder_ctl00'],
                //海航实业
                hnasyportal:['#pageNavigationBar_ctl00','#scrollbox_Announcemen_JiTuan','#scrollbox_Announcemen','#pageLayout_ctl00_ctl09_ctl00_ctl02_ClientPagepartContainer','#pageLayout_ctl00_ctl13_ctl00_ctl02_ClientPagepartContainer','#pageLayout_ctl00_ctl14_ctl00_fragmentHolder_ctl00','#pageLayout_ctl00_ctl10_ctl00_fragmentHolder_ctl00','#pageLayout_ctl00_ctl12_ctl00_fragmentHolder_ctl00','#pageLayout_ctl00_ctl04_ctl00_fragmentHolder_ctl00_contentContainer','#pageLayout_ctl00_ctl08_ctl00_fragmentHolder_ctl00','#pageLayout_ctl00_ctl05_ctl00_fragmentHolder_ctl00','#pageLayout_ctl00_ctl03_ctl00_fragmentHolder_ctl00_labAD','#pageLayout_ctl00_ctl15_ctl00_fragmentHolder_ctl00'],
                //海航旅业
                tourismportal:['#pageNavigationBar_ctl00','#scrollbox_Announcemen_JiTuan','#scrollbox_Announcemen','#pageLayout_ctl00_ctl09_ctl00_ctl02_ClientPagepartContainer','#pageLayout_ctl00_ctl14_ctl00_ctl02_ClientPagepartContainer','#pageLayout_ctl00_ctl15_ctl00_ctl02_ClientPagepartContainer','#pageLayout_ctl00_ctl13_ctl00_fragmentHolder_ctl00','#pageLayout_ctl00_ctl07_ctl00_fragmentHolder_ctl00','#pageLayout_ctl00_ctl10_ctl00_fragmentHolder_ctl00','#pageLayout_ctl00_ctl06_ctl00_fragmentHolder_ctl00_contentContainer','#pageLayout_ctl00_ctl08_ctl00_fragmentHolder_ctl00','#pageLayout_ctl00_ctl05_ctl00_fragmentHolder_ctl00','#pageLayout_ctl00_ctl02_ctl00_fragmentHolder_ctl00_labAD','#pageLayout_ctl00_ctl16_ctl00_fragmentHolder_ctl00']
            },
            cfg = null,
            img= new Image(1,1);

        if(key){
            key = key.toLowerCase()
        }else{
            return;
        }
        cfg = blackConfig[key];
        if(!cfg){
            return
        }
        for(var i=0 ; i<_sb.length ; i++){
            data[_sb[i][0]] =_sb[i][1];
        }
        data[cookieid] = Analytics.getCookie(cookieid);
        var blockName;
        for(var i=cfg.length-1;i>=0;i--){
            blockName = cfg[i];
            $(blockName).attr('blk_id',cfg[i]);
            $(blockName,'body').live('click',function(evt){
                //evt.target.parentNode.tagName 是为了兼容"招聘，专栏，期刊"下连接套嵌图片
                if(evt.target.tagName =='A' || evt.target.parentNode.tagName =='A'){
                    var a = evt.target.tagName =='A'?evt.target:evt.target.parentNode;
                    var blk_id = this.id,
                        url =a.href;
                    if(url.indexOf('javascript:') == -1){
                        blockAnalysis(url,blk_id);
                    }else{
                        try{
                            if(url.indexOf('javascript:opencontent')>-1){
                                blockAnalysis(/javascript:opencontent\(['"](.*?)['"]\)/.exec(url)[1],blk_id);
                            }else if(url.indexOf('javascript:OpenNew')>-1){
                                blockAnalysis(/javascript:OpenNew\(['"](.*?)['"]\)/.exec(url)[1],blk_id);
                            }else{
                                blockAnalysis(/javascript\:[a-z,A-Z,\-]+\(['"](.*?)['"]\)/.exec(url)[1],blk_id);
                            }
                        }catch(error){

                        }
                    }
                }

            })
        }
        function blockAnalysis(url, click_id){
            if(!click_id){
                return
            }
            data.url = url;
            data[cookieid] = data[cookieid] || Analytics.getCookie(cookieid);
            data.click_id=click_id;
            data.category = key;
            img.src =  'http://webstat.chinaface.com/index.php/analytics/collector?'+$.param(data);
        }
    }catch(error){

    }
})()
/**************************鹏程网首页,各模块统计  END   **************************/

Analytics.sendinfo();