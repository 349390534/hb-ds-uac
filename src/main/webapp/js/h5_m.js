(function() {
	var _ha_proid = "5002";
    var _true = true
      , _false = false
      , _encodeURI = encodeURIComponent
      , _o_window = window
      , _undefined = undefined
      , _o_string = String
      , _o_math = Math
      , _push = "push"
      , _cookie = "cookie"
      , _charAt = "charAt"
      , _str_indexOf = "indexOf"
      , _gaGlobal = "gaGlobal"
      , _getTime = "getTime"
      , _toString = "toString"
      , _str_window = "window"
      , _length = "length"
      , _str_document = "document"
      , _split = "split"
      , _location = "location"
      , _protocol = "protocol"
      , _href = "href"
      , _substring = "substring"
      , _join = "join"
      , _toLowerCase = "toLowerCase";
    var _hat_ = "_hat"
      , _haq_ = "_haq"
      , _version_ = "1.1.1"
      , _gaUserPrefs_ = "_gaUserPrefs"
      , _ioo_ = "ioo"
      , _amp_ = "&"
      , _equal_ = "="
      , _param_hutma = "__hutma="
      , _param_hutmb = "__hutmb="
      , _param_hutmc = "__hutmc="
      , _param_hutmk = "__hutmk="
      , _param_hutmv = "__hutmv="
      , _param_hutmz = "__hutmz="
      , _param_hutmx = "__hutmx="
	  , _param_hutmmobile = "__hutmmobile="
	  , _param_hb_pgid = "_hb_pgid="
	  , _param_hbotrack = "_hbotrack="
	  , _param_zero_hbtrack = "_zero_hbtrack="
      , _param_gaso = "GASO="
      ,howbuy="howbuy.com"
      ,hbc="www.howbuy.com"
  	  ,ehbc="www.ehowbuy.com"
  	  ,ehbc_domain=".ehowbuy.com";
    //Two years in millseconds.
    var COOKIE_USER_PERSISTENCE = 63072000000;
    var BrowserDetect = {
        init: function() {
            this.browser = this.searchString(this.dataBrowser) || "";
            this.version = this.searchVersion(navigator.userAgent) || this.searchVersion(navigator.appVersion) || "";
            this.OS = this.searchString(this.dataOS) || ""
        },
        searchString: function(data) {
            for (var i = 0; i < data.length; i++) {
                var dataString = data[i].string;
                var dataProp = data[i].prop;
                this.versionSearchString = data[i].versionSearch || data[i].identity;
                if (dataString) {
                    if (dataString.indexOf(data[i].subString) != -1) {
                        return data[i].identity
                    }
                } else {
                    if (dataProp) {
                        return data[i].identity
                    }
                }
            }
        },
        searchVersion: function(dataString) {
            var index = dataString.indexOf(this.versionSearchString);
            if (index == -1) {
                return
            }
            return parseFloat(dataString.substring(index + this.versionSearchString.length + 1))
        },
        dataBrowser: [{
            string: navigator.userAgent,
            subString: "Chrome",
            identity: "Chrome"
        }, {
            string: navigator.userAgent,
            subString: "OmniWeb",
            versionSearch: "OmniWeb/",
            identity: "OmniWeb"
        }, {
            string: navigator.vendor,
            subString: "Apple",
            identity: "Safari",
            versionSearch: "Version"
        }, {
            prop: window.opera,
            identity: "Opera"
        }, {
            string: navigator.vendor,
            subString: "iCab",
            identity: "iCab"
        }, {
            string: navigator.vendor,
            subString: "KDE",
            identity: "Konqueror"
        }, {
            string: navigator.userAgent,
            subString: "Firefox",
            identity: "FF"
        }, {
            string: navigator.vendor,
            subString: "Camino",
            identity: "Camino"
        }, {
            string: navigator.userAgent,
            subString: "Netscape",
            identity: "Netscape"
        }, {
            string: navigator.userAgent,
            subString: "MSIE",
            identity: "IE",
            versionSearch: "MSIE"
        }, {
            string: navigator.userAgent,
            subString: "Gecko",
            identity: "Mozilla",
            versionSearch: "rv"
        }, {
            string: navigator.userAgent,
            subString: "Mozilla",
            identity: "Netscape",
            versionSearch: "Mozilla"
        }],
        dataOS: [{
            string: navigator.platform,
            subString: "Win",
            identity: "Windows"
        }, {
            string: navigator.platform,
            subString: "Mac",
            identity: "Mac"
        }, {
            string: navigator.userAgent,
            subString: "iPhone",
            identity: "iPhone/iPod"
        }, {
            string: navigator.platform,
            subString: "Linux",
            identity: "Linux"
        }]
    };
    BrowserDetect.init();
    var HRegistry = function() {
        var oThis = this
          , _registry = []
          , _alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";
        oThis.register = function(idx) {
            _registry[idx] = _true
        }
        ;
        oThis.getUTMU = function() {
            for (var arr_hutmu = [], i = 0; i < _registry[_length]; i++) {
                if (_registry[i]) {
                    arr_hutmu[_o_math.floor(i / 6)] ^= 1 << i % 6
                }
            }
            for (i = 0; i < arr_hutmu[_length]; i++) {
                arr_hutmu[i] = _alphabet[_charAt](arr_hutmu[i] || 0)
            }
            return arr_hutmu[_join]("")
        }
    }
      , registry = new HRegistry;
    function HRegister(idx) {
        registry.register(idx)
    }
    var HGlobal = function(win, doc) {
        var oThis = this;
        oThis.window = win;
        oThis.document = doc;
        oThis.setTimeout = function(callback, delay) {
            setTimeout(callback, delay)
        }
        ;
        oThis.contains = function(key) {
            return navigator.userAgent[_str_indexOf](key) >= 0
        }
        ;
        oThis.isFirefox = function() {
            return oThis.contains("Firefox") && ![].reduce
        }
        ;
        oThis.processSource = function(source) {
            if (!source || !oThis.contains("Firefox")) {
                return source
            }
            source = source.replace(/\n|\r/g, " ");
            for (var i = 0, len = source[_length]; i < len; ++i) {
                var charCode = source.charCodeAt(i) & 255;
                if (charCode == 10 || charCode == 13) {
                    source = source[_substring](0, i) + "?" + source[_substring](i + 1)
                }
            }
            return source
        }
    }
      , $HGlobal = new HGlobal(_o_window,document);
    var HCreateBinder = function(o) {
        return function(method, idx, func) {
            o[method] = function() {
                HRegister(idx);
                return func.apply(o, arguments)
            }
            ;
            return func
        }
    }
      , AddListener = function(element, type, listener, useCapture) {
        if (element.addEventListener) {
            element.addEventListener(type, listener, !!useCapture)
        } else {
            element.attachEvent && element.attachEvent("on" + type, listener)
        }
    }
      , IsArray = function(o) {
        return Object.prototype[_toString].call(Object(o)) == "[object Array]"
    }
      , IsEmpty = function(o) {
        return ( (_undefined == o) || ("-" == o) || ("" == o)) 
    }
      , Pick = function(map, key, separator) {
        var result = "-", idx;
        if (!IsEmpty(map) && !IsEmpty(key) && !IsEmpty(separator)) {
            idx = map[_str_indexOf](key);
            if (idx > -1) {
                var endIdx = map[_str_indexOf](separator, idx);
                if (endIdx < 0) {
                    endIdx = map[_length]
                }
                result = map[_substring](idx + key[_str_indexOf](_equal_) + 1, endIdx)
            }
        }
        return result
    }
      , CheckHash = function(o) {
        var result = _false, code = 0, i, _char;
        if (!IsEmpty(o)) {
            result = _true;
            for (i = 0; i < o[_length]; i++) {
                _char = o[_charAt](i);
                code += "." == _char ? 1 : 0;
                result = result && code <= 1 && (0 == i && "-" == _char || ".0123456789"[_str_indexOf](_char) > -1)
            }
        }
        return result
    }
      , Encode = function(uri, isAll) {
        var _encode = _encodeURI;
        if (_encode instanceof Function) {
            return isAll ? encodeURI(uri) : _encode(uri)
        } else {
            HRegister(68);
            return escape(uri)
        }
    }
      , Decode = function(encodedURI, isAll) {
        var _decode = decodeURIComponent, uri;
        encodedURI = encodedURI[_split]("+")[_join](" ");
        if (_decode instanceof Function) {
            try {
                uri = isAll ? decodeURI(encodedURI) : _decode(encodedURI)
            } catch (ex) {
                HRegister(17);
                uri = unescape(encodedURI)
            }
        } else {
            HRegister(68);
            uri = unescape(encodedURI)
        }
        return uri
    }
      , Contains = function(str, sub) {
        return ( str[_str_indexOf](sub) > -1) 
    }
    ;
    function Trim(str) {
        if (!str || "" == str) {
            return ""
        }
        for (; str[_charAt](0)[_length] > 0 && " \n\r\t"[_str_indexOf](str[_charAt](0)) > -1; ) {
            str = str[_substring](1)
        }
        for (; str[_charAt](str[_length] - 1)[_length] > 0 && " \n\r\t"[_str_indexOf](str[_charAt](str[_length] - 1)) > -1; ) {
            str = str[_substring](0, str[_length] - 1)
        }
        return str
    }
    var HPush = function(arr, value) {
        arr[_push] || HRegister(94);
        arr[arr[_length]] = value
    }
      , Hash = function(str) {
        var hash = 1, charCode = 0, idx;
        if (!IsEmpty(str)) {
            hash = 0;
            for (idx = str[_length] - 1; idx >= 0; idx--) {
                charCode = str.charCodeAt(idx);
                hash = (hash << 6 & 268435455) + charCode + (charCode << 14);
                charCode = hash & 266338304;
                hash = charCode != 0 ? hash ^ charCode >> 21 : hash
            }
        }
        return hash
    }
      , Random = function() {
        return _o_math.round(_o_math.random() * 2147483647)
    }
      , emptyFunction = function() {}
    ;
    var HOrganic = function(engine, keyword) {
        this.engine = engine;
        this.keyword = keyword
    }
      , Config = function() {
        function parseOrganicData(data) {
            var organic = [];
            data = data[_split](",");
            for (var item, i = 0; i < data[_length]; i++) {
                item = data[i][_split](":");
                organic[_push](new HOrganic(item[0],item[1]))
            }
            return organic
        }
        var oThis = this;
        oThis.campaign = "hutm_campaign";
        oThis.content = "hutm_content";
        oThis.id = "hutm_id";
        oThis.medium = "hutm_medium";
        oThis.nooverride = "hutm_nooverride";
        oThis.source = "hutm_source";
        oThis.term = "hutm_term";
        oThis.clid = "gclid";
        oThis._hutmmobile = "__hutmmobile";
    	oThis._hb_pgid = "_hb_pgid";
		oThis._hbotrack = "_hbotrack";
		oThis._zero_hbtrack = "_zero_hbtrack";
        
        oThis.allowAnchor = 0;
        oThis.allowLinker = 0;
        oThis.campaignCookieTimeout = 15768000000;
        oThis.sessionCookieTimeout = 1800000;
        oThis.visitorCookieTimeout = 63072000000;
        oThis.ignoredOrganic = [];
        oThis.ignoredReferral = [];
        oThis.googleCustomSearchPath = "cse";
        oThis.googleKey = "q";
        oThis.maxCustomVariables = 50;
        oThis.organic = parseOrganicData("images.google:q,google:q,yahoo:p,yahoo:q,msn:q,bing:q,baidu:wd,baidu:word,360:q,sogou:query,tom:word,163:q,soso:w,3721:p,yisou:search,zhongsou:w,live:q,iask:k,openfind:q,qq:k");
        oThis.cookiePath = "/";
        oThis.sampleRate = 100;
        oThis.localGifPath = "/__hutm.gif";
        oThis.detectTitle = 1;
        oThis.detectFlash = 1;
        oThis.transactionDelim = "|";
        oThis.clientInfo = 1;
        oThis.campaignTrack = 1;
        oThis.allowHash = 1;
        //oThis.domain = "auto";
        oThis.domain = ehbc_domain;
        oThis.localServerMode = 1;
        oThis.Sc = 10;
        oThis.Qb = 10;
        oThis.Tc = 0.2;
        oThis.namespace = _undefined
    }
    ;
    var HCookie = function(config) {
        function pick(cookie, param, namespace, ignoreExpires) {
            var value = ""
              , expires = 0;
            value = Pick(cookie, "2" + param, ";");
            if (!IsEmpty(value)) {
                var idx = value[_str_indexOf]("^" + namespace + ".");
                if (idx < 0) {
                    return ["", 0]
                }
                value = value[_substring](idx + namespace[_length] + 2);
                if (value[_str_indexOf]("^") > 0) {
                    value = value[_split]("^")[0]
                }
                var values = value[_split](":");
                value = values[1];
                expires = parseInt(values[0], 10);
                if (!ignoreExpires && expires < oThis.currentTime) {
                    value = ""
                }
            }
            if (IsEmpty(value)) {
                value = ""
            }
            return [value, expires]
        }
        function combine(value, namespace) {
            return "^" + [[namespace, value[1]][_join]("."), value[0]][_join](":")
        }
        function getCookieExpires(timeout) {
            var date = new Date;
            timeout = new Date(date[_getTime]() + timeout);
            return "expires=" + timeout.toGMTString() + "; "
        }
        var oThis = this
          , _config = config;
        oThis.currentTime = (new Date)[_getTime]();
        var params = [_param_hutma, _param_hutmb, _param_hutmc, _param_hutmz, _param_hutmv, _param_hutmx, _param_gaso];
        oThis.getCookie = function() {
            var cookie = $HGlobal[_str_document][_cookie];
            return _config.namespace ? oThis.getCookieByNamespace(cookie, _config.namespace) : cookie
        }
        ;
        oThis.getCookieByNamespace = function(cookie, namespace) {
            for (var arrParams = [], value, i = 0; i < params[_length]; i++) {
                value = pick(cookie, params[i], namespace)[0];
                IsEmpty(value) || (arrParams[arrParams[_length]] = params[i] + value + ";")
            }
            return arrParams[_join]("")
        }
        ;
        oThis.setCookie = function(key, value, timeout) {
            var cookie = timeout > 0 ? getCookieExpires(timeout) : "";
            if (_config.namespace) {
                value = oThis.setCookieByNamespace($HGlobal[_str_document][_cookie], key, _config.namespace, value, timeout);
                key = "2" + key;
                cookie = timeout > 0 ? getCookieExpires(_config.visitorCookieTimeout) : ""
            }
            key += value;
            key = $HGlobal.processSource(key);
            if (key[_length] > 2000) {
                HRegister(69);
                key = key[_substring](0, 2000)
            }
            cookie = key + "; path=" + _config.cookiePath + ";" + cookie + oThis.getCookieDomain();
            $HGlobal[_str_document].cookie = cookie;
        }
        ;
        oThis.setCookieByNamespace = function(cookie, key, namespace, value, timeout) {
            var _value = "";
            timeout = timeout || _config.visitorCookieTimeout;
            value = combine([value, oThis.currentTime + timeout * 1], namespace);
            _value = Pick(cookie, "2" + key, ";");
            if (!IsEmpty(_value)) {
                cookie = combine(pick(cookie, key, namespace, _true), namespace);
                _value = _value[_split](cookie)[_join]("");
                return _value = value + _value
            }
            return value
        }
        ;
        oThis.getCookieDomain = function() {
            return IsEmpty(_config.domain) ? "" : "domain=" + _config.domain + ";"
        };
        oThis.getCookieByName = function(name){
        	
        	var ck = $HGlobal[_str_document].cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
        	if(ck)return ck[2];
        };
    }
    ;
    var HaCookie = function(config) {
        function join(arr) {
            arr = IsArray(arr) ? arr[_join](".") : "";
            return IsEmpty(arr) ? "-" : arr
        }
        function split(str, n) {
            var result = [], i;
            if (!IsEmpty(str)) {
                result = str[_split](".");
                if (n) {
                    for (i = 0; i < result[_length]; i++) {
                        CheckHash(result[i]) || (result[i] = "-")
                    }
                }
            }
            return result
        }
        function initUTMParams(map, domainHash, separator) {
            var _hutmParams = oThis.UTMParams, i, param;
            for (i = 0; i < _hutmParams[_length]; i++) {
                param = _hutmParams[i][0];
                param += IsEmpty(domainHash) ? domainHash : domainHash + _hutmParams[i][4];
                _hutmParams[i][2](Pick(map, param, separator))
            }
        }
        var arrUTMA, arrUTMB, arrUTMC, arrUTMZ, _arrUTMV, _hutmx, _GASO, oThis = this, _hutmParamsHash, _config = config;
        oThis.cookie = new HCookie(config);
        oThis.checkParamsHash = function() {
            return _undefined == _hutmParamsHash || _hutmParamsHash == oThis.hashUTMParams()
        }
        ;
        oThis.getCookie = function() {
            return oThis.cookie.getCookie()
        }
        ;
        oThis.getUTMX = function() {
            return _hutmx ? _hutmx : "-"
        }
        ;
        oThis.setUTMX = function(hutmx) {
            _hutmx = hutmx
        }
        ;
        oThis.setUTMParamsHash = function(hash) {
            _hutmParamsHash = CheckHash(hash) ? hash * 1 : "-"
        }
        ;
        oThis.getUTMV = function() {
            return join(_arrUTMV)
        }
        ;
        oThis.setUTMV = function(hutmv) {
            _arrUTMV = split(hutmv)
        }
        ;
        oThis.deleteUTMVCookie = function() {
            oThis.cookie.setCookie(_param_hutmv, "", -1)
        }
        ;
        oThis.getUTMParamsHash = function() {
            return _hutmParamsHash ? _hutmParamsHash : "-"
        }
        ;
        oThis.getCookieDomain = function() {
            return IsEmpty(_config.domain) ? "" : "domain=" + _config.domain + ";"
        }
        ;
        oThis.getUTMA = function() {
            return join(arrUTMA)
        }
        ;
        oThis.setUTMA = function(hutma) {
            arrUTMA = split(hutma, 1)
        }
        ;
        oThis.getUTMB = function() {
            return join(arrUTMB)
        }
        ;
        oThis.setUTMB = function(hutmb) {
            arrUTMB = split(hutmb, 1)
        }
        ;
        oThis.getUTMC = function() {
            return join(arrUTMC)
        }
        ;
        oThis.setUTMC = function(hutmc) {
            arrUTMC = split(hutmc, 1)
        }
        ;
        oThis.getUTMZ = function() {
            return join(arrUTMZ)
        }
        ;
        oThis.setUTMZ = function(hutmz) {
            arrUTMZ = split(hutmz);
            for (var i = 0; i < arrUTMZ[_length]; i++) {
                if (i < 4 && !CheckHash(arrUTMZ[i])) {
                    arrUTMZ[i] = "-"
                }
            }
        }
        ;
        oThis.getGASO = function() {
            return _GASO
        }
        ;
        oThis.setGASO = function(gaso) {
            _GASO = gaso
        }
        ;
        oThis.initialize = function() {
            arrUTMA = [];
            arrUTMB = [];
            arrUTMC = [];
            arrUTMZ = [];
            _hutmx = _undefined;
            _arrUTMV = [];
            _hutmParamsHash = _undefined
        }
        ;
        oThis.hashUTMParams = function() {
            for (var params = "", i = 0; i < oThis.UTMParams[_length]; i++) {
                params += oThis.UTMParams[i][1]()
            }
            return Hash(params)
        }
        ;
        oThis.setDomainHashByCookie = function(domainHash) {
            var cookie = oThis.getCookie()
              , result = _false;
            if (cookie) {
                initUTMParams(cookie, domainHash, ";");
                oThis.setUTMParamsHash(_o_string(oThis.hashUTMParams()));
                result = _true
            }
            return result
        }
        ;
        oThis.setUTMKHashByLocation = function(params) {
            initUTMParams(params, "", _amp_);
            oThis.setUTMParamsHash(Pick(params, _param_hutmk, _amp_))
        }
        ;
        oThis.getParams = function() {
            var _hutmParams = oThis.UTMParams, _params = [], i;
            for (i = 0; i < _hutmParams[_length]; i++) {
                HPush(_params, _hutmParams[i][0] + _hutmParams[i][1]())
            }
            HPush(_params, _param_hutmk + oThis.hashUTMParams());
            return _params[_join](_amp_)
        }
        ;
        oThis.copyCookiePath = function(domainHash, cookiePath) {
            var _hutmParams = oThis.UTMParams
              , _cookiePath = _config.cookiePath;
            oThis.setDomainHashByCookie(domainHash);
            _config.cookiePath = cookiePath;
            for (var i = 0; i < _hutmParams[_length]; i++) {
                if (!IsEmpty(_hutmParams[i][1]())) {
                    _hutmParams[i][3]();
                    _config.cookiePath = _cookiePath
                }
            }
        }
        ;
        oThis.setUTMACookie = function() {
            oThis.cookie.setCookie(_param_hutma, oThis.getUTMA(), _config.visitorCookieTimeout)
        }
        ;
        oThis.setUTMBCookie = function() {
            oThis.cookie.setCookie(_param_hutmb, oThis.getUTMB(), _config.sessionCookieTimeout)
        }
        ;
        oThis.setUTMCCookie = function() {
            oThis.cookie.setCookie(_param_hutmc, oThis.getUTMC(), 0)
        }
        ;
        oThis.setUTMZCookie = function() {
            oThis.cookie.setCookie(_param_hutmz, oThis.getUTMZ(), _config.campaignCookieTimeout)
        }
        ;
        oThis.setUTMXCookie = function() {
            oThis.cookie.setCookie(_param_hutmx, oThis.getUTMX(), _config.visitorCookieTimeout)
        }
        ;
        oThis.setUTMVCookie = function() {
            oThis.cookie.setCookie(_param_hutmv, oThis.getUTMV(), _config.visitorCookieTimeout)
        }
        ;
        oThis.setGASOCookie = function() {
            oThis.cookie.setCookie(_param_gaso, oThis.getGASO(), 0)
        }
        ;
        oThis.UTMParams = [[_param_hutma, oThis.getUTMA, oThis.setUTMA, oThis.setUTMACookie, "."], [_param_hutmb, oThis.getUTMB, oThis.setUTMB, oThis.setUTMBCookie, ""], [_param_hutmc, oThis.getUTMC, oThis.setUTMC, oThis.setUTMCCookie, ""], [_param_hutmx, oThis.getUTMX, oThis.setUTMX, oThis.setUTMXCookie, ""], [_param_hutmz, oThis.getUTMZ, oThis.setUTMZ, oThis.setUTMZCookie, "."], [_param_hutmv, oThis.getUTMV, oThis.setUTMV, oThis.setUTMVCookie, "."]]
    }
    ;

    //UUID生产对象
    var UUID = function(){
    	this.uuid=function(len, radix) {
    		  var chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
    		  var uuid = [],i;
    		  radix = radix || chars.length;
    		  if (len) {
    		    // Compact form
    		    for (i = 0; i < len; i++) uuid[i] = chars[0 | Math.random() * radix];
    		  } else {
    		    // rfc4122, version 4 form
    		    var r;
    		    // rfc4122 requires these characters
    		    uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-';
    		    uuid[14] = '4';
    		    // Fill in random data.  At i==19 set the high bits of clock sequence as
    		    // per rfc4122, sec. 4.1.5
    		    for (i = 0; i < 36; i++) {
    		      if (!uuid[i]) {
    		        r = 0 | Math.random() * 16;
    		        uuid[i] = chars[(i == 19) ? (r & 3) | 8 : r];
    		      }
    		    }
    		  }
    		  return uuid.join('');
    		}
    };
    var PCookie = function(config){
    	var _oThis = this,
    	_config = config;
    	_oThis.hcookie = new HCookie(_config);
    	_oThis.setVisitCookie = function(visitCode){
    		_oThis.hcookie.setCookie(_param_hutmmobile, visitCode, COOKIE_USER_PERSISTENCE);
    	};
    	_oThis.getVisitCookie = function(){
    		return _oThis.hcookie.getCookieByName(_config._hutmmobile);
    	};
    	_oThis.setPageIdCookie = function(pageId){
    		_oThis.hcookie.setCookie(_param_hb_pgid, pageId, 0);
    	};
    	_oThis.getPageIdCookie = function(){
    		return _oThis.hcookie.getCookieByName(_config._hb_pgid);
    	};
    	_oThis.setOtrackCookie = function(hbotrack){
    		_oThis.hcookie.setCookie(_param_hbotrack, hbotrack, 0);
    		
    	};
    	_oThis.getOtrackCookie = function(){
    		return  _oThis.hcookie.getCookieByName(_config._hbotrack);
    	};
    	_oThis.setZeroTrackCookie = function(zeroTrack){
    		_oThis.hcookie.setCookie(_param_zero_hbtrack, zeroTrack, 30 * 24 * 60 * 60 * 1000);
    	};
    	_oThis.getZeroTrackCookie = function(){
    		return _oThis.hcookie.getCookieByName(_config._zero_hbtrack);
    	};
    };
    var google_analytics_domain = "";
    google_analytics_path = google_analytics_domain + "p/__hutm.gif",
    Ajax = function() {
        var oThis = this;
        oThis.send = function(url, param, mark, callback, _ioo) {
            if (param[_length] <= 2036 || _ioo) {
                oThis.sendByImage(url + "?" + param, callback)
            } else {
                if (param[_length] <= 8192) {
                    $HGlobal.isFirefox() ? oThis.sendByImage(url + "?" + mark + "&err=ff2post&len=" + param[_length], callback) : oThis.Send(param, callback)
                } else {
                    oThis.sendByImage(url + "?" + mark + "&err=len&max=8192&len=" + param[_length], callback)
                }
            }
        }
        ;
        oThis.sendByImage = function(src, callback) {
            var image = new Image(1,1);
            image.src = src;
            image.onload = function() {
                image.onload = null ;
                (callback || emptyFunction)()
            }
        }
        ;
        oThis.Send = function(param, callback) {
            oThis.sendByRequest(param, callback) || oThis.sendByIFrame(param, callback)
        }
        ;
        oThis.sendByRequest = function(param, callback) {
            var request, Request = $HGlobal[_str_window].XDomainRequest;
            if (Request) {
                request = new Request;
                request.open("POST", google_analytics_path)
            } else {
                if (Request = $HGlobal[_str_window].XMLHttpRequest) {
                    Request = new Request;
                    if ("withCredentials" in Request) {
                        request = Request;
                        request.open("POST", google_analytics_path, _true);
                        request.setRequestHeader("Content-Type", "text/plain")
                    }
                }
            }
            if (request) {
                request.onreadystatechange = function() {
                    if (request.readyState == 4) {
                        callback && callback();
                        request = null 
                    }
                }
                ;
                request.send(param);
                return _true
            }
            return _false
        }
        ;
        oThis.sendByIFrame = function(param, callback) {
            var doc = $HGlobal[_str_document];
            if (doc.body) {
                param = _encodeURI(param);
                try {
                    var fra = doc.createElement('<iframe name="' + param + '"></iframe>')
                } catch (ex) {
                    fra = doc.createElement("iframe");
                    fra.name = param
                }
                fra.height = "0";
                fra.width = "0";
                fra.style.display = "none";
                fra.style.visibility = "hidden";
                var _loca = doc[_location];
                _loca = _loca[_protocol] + "//" + _loca.host + "/favicon.ico";
                _loca = google_analytics_domain + "u/post_iframe.html#" + _encodeURI(_loca);
                var setFra = function() {
                    fra.src = "";
                    fra.parentNode && fra.parentNode.removeChild(fra)
                }
                ;
                AddListener($HGlobal[_str_window], "beforeunload", setFra);
                var succes = _false
                  , i = 0
                  , fraLoad = function() {
                    if (!succes) {
                        try {
                            if (i > 9 || fra.contentWindow[_location].host == doc[_location].host) {
                                succes = _true;
                                setFra();
                                var win = $HGlobal[_str_window]
                                  , _beforeunload = "beforeunload"
                                  , _setFra = setFra;
                                if (win.removeEventListener) {
                                    win.removeEventListener(_beforeunload, _setFra, _false)
                                } else {
                                    win.detachEvent && win.detachEvent("on" + _beforeunload, _setFra)
                                }
                                callback && callback();
                                return
                            }
                        } catch (ex) {}
                        i++;
                        $HGlobal.setTimeout(fraLoad, 200)
                    }
                }
                ;
                AddListener(fra, "load", fraLoad);
                doc.body.appendChild(fra);
                fra.src = _loca
            } else {
                $HGlobal.setTimeout(function() {
                    oThis.sendByIFrame(param, callback)
                }, 100)
            }
        }
    }
    ;
    var Map = function () {
        var map = function(key, value) {
            this.key = key;
            this.value = value
        }
        ;
        var put = function(key, value) {
            for (var i = 0; i < this.arr.length; i++) {
                if (this.arr[i].key === key) {
                    this.arr[i].value = value;
                    return;
                }
            }
            this.arr[this.arr.length] = new map(key,value);
        }
        ;
        var get = function(key) {
            for (var i = 0; i < this.arr.length; i++) {
                if (this.arr[i].key === key) {
                    return this.arr[i].value
                }
            }
            return null 
        }
        ;
        var remove = function(key) {
            var v;
            for (var i = 0; i < this.arr.length; i++) {
                v = this.arr.pop();
                if (v.key === key) {
                    continue
                }
                this.arr.unshift(v)
            }
        }
        ;
        var size = function() {
            return this.arr.length
        }
        ;
        var isEmpty = function() {
            return this.arr.length <= 0
        }
        ;
        var keyset = function() {
            var keyset = new Array();
            for (var i = 0; i < this.arr.length; i++) {
                keyset[i] = this.arr[i].key
            }
            return keyset
        }
        ;
        this.arr = new Array();
        this.map = map;
        this.get = get;
        this.put = put;
        this.remove = remove;
        this.size = size;
        this.isEmpty = isEmpty;
        this.keyset = keyset
    };
    var zeroChannelTagMap = function(){
    	var map = new Map(); 
    	map.put("sogou.com","0.1800000");
    	map.put("baidu.com","0.2800000");
    	map.put("google.com","0.3800000");
    	map.put("haosou.com","0.4800000");
    	map.put("bing.com","0.5800000");
    	map.put("qq.com","0.7800000");
    	map.put("ifeng.com","0.8800000");
    	map.put("go.uc.cn","0.9800000");
    	map.put("hao123.com","0.10800000");
    	map.put("sina.com.cn","0.11800000");
    	map.put("youdao.com","0.12800000");
    	map.put("maxthon.cn","0.13800000");
    	map.put("其他","0.9999999998");
    	
    	this.getZeroChannelTagMap=function(){
    		return map;
    	};
    	this.getOtherChannelTag=function(){
    		return "0.9999999998";
    	};
    };
    // 获取公共参数对象
    var localParamDataUtil = function(){
    	var oThis = this;
    	oThis.getHtag=function(){
    		var _hTag = oThis.getRequestParam("" + document.location.href + "", "HTAG").split("#")[0];
    		return _hTag;
    	};
    	oThis.getPageId=function(){
    		var p = document.getElementById("pageid");
			var pageId = (p==null||p==undefined)?"":p.value;
			pageId = pageId?pageId:"";
			return pageId;
    	};
    	oThis.getPageLevel=function(){
    		var pl = document.getElementById("pagelevel");
    		var pagelevel = (pl==null||pl==undefined)?"":pl.value;
			pagelevel = pagelevel?pagelevel:"";
			return pagelevel;
    	};
    	oThis.getRequestParam = function(url, paramName) {
             var paraString = url.substring(url.indexOf("?") + 1).split("&");
             var paraObj = {};
             for (i = 0; j = paraString[i]; i++) {
                 paraObj[j.substring(0, j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=") + 1, j.length)
             }
             var returnValue = paraObj[paramName.toLowerCase()];
             if (typeof (returnValue) == "undefined") {
                 return "";
             } else {
                 return returnValue;
             }
         }
    };
    var GaAjax = function(config) {
        var oThis = this
          , _config = config
          , _gaCookie = new HaCookie(_config)
          , ajax = new Ajax
          ,paramUtil = new localParamDataUtil
          , _ioo = !_HTrackerFactory.getGaUserPrefs()
          , emptyFunction = function() {}
        ;
        oThis.getHowbuyAnalyticsPath = function() {
        	return (window.location.protocol == "https:") ? "https://"+ehbc+"/uac/ha5.do" : "http://"+ehbc+"/uac/ha5.do"
        }
        ;
        oThis.customParam = function(param) {
        	
			var _hTag = paramUtil.getHtag();
			var pageId = paramUtil.getPageId();
			var pagelevel = paramUtil.getPageLevel();
			var _proId  = _ha_proid;
			return "&HTAG=" + _hTag + "&pageid=" + pageId + "&pagelevel=" + pagelevel + "&proid=" + _proId;
		}
        ;
        oThis.send = function(param, account, domainHash, e, d, callback) {
            var localServerMode = _config.localServerMode
              , location = $HGlobal[_str_document][_location];
            _gaCookie.setDomainHashByCookie(domainHash);
            var arr_hutmb = _gaCookie.getUTMB()[_split](".");
            e = true;
            if (arr_hutmb[1] < 500 || e) {
                if (d) {
                    var _time = (new Date)[_getTime](), o;
                    o = (_time - arr_hutmb[3]) * (_config.Tc / 1000);
                    if (o >= 1) {
                        arr_hutmb[2] = _o_math.min(_o_math.floor(arr_hutmb[2] * 1 + o), _config.Qb);
                        arr_hutmb[3] = _time
                    }
                }
                if (e || !d || arr_hutmb[2] >= 1) {
                    if (!e && d) {
                        arr_hutmb[2] = arr_hutmb[2] * 1 - 1
                    }
                    arr_hutmb[1] = arr_hutmb[1] * 1 + 1;
                    var _version = "hutmwv=" + _version_;
                    _time = "&hutmn=" + Random();
                    var mark = _version + "e" + _time;
                    param = _version + _time + (IsEmpty(location.hostname) ? "" : "&hutmhn=" + Encode(location.hostname)) + (_config.sampleRate == 100 ? "" : "&hutmsp=" + Encode(_config.sampleRate)) + param;
                    if (0 == localServerMode || 2 == localServerMode) {
                        callback = 2 == localServerMode ? emptyFunction : callback || emptyFunction;
                        param += "&hutmcch=" + paramUtil.getRequestParam("'" + location + "'", "hutmcch");
                        _ioo && ajax.send(_config.localGifPath, param, mark, callback, _true)
                    }
                    if (1 == localServerMode || 2 == localServerMode) {
                        account = "&hutmac=" + account;
                        mark += account;
                        param += account + "&hutmcc=" + oThis.getCheckCode(domainHash);
                        if (_HTrackerFactory.anonymizeIp) {
                            domainHash = "&aip=1";
                            mark += domainHash;
                            param += domainHash
                        }
                        param += "&hutmu=" + registry.getUTMU();
                        param += "&hutmcch=" + paramUtil.getRequestParam("'" + location + "'", "hutmcch");
                        
                        param += oThis.customParam(param);
                        _ioo && ajax.send(oThis.getHowbuyAnalyticsPath(), param, mark, callback)
                    }
                }
            }
            urlParam = param;
            _gaCookie.setUTMB(arr_hutmb[_join]("."));
            _gaCookie.setUTMBCookie()
        }
        ;
        oThis.getCheckCode = function(domainHash) {
            for (var _params_hash = [], params = [_param_hutma, _param_hutmz, _param_hutmv, _param_hutmx], cookie = _gaCookie.getCookie(), _domainHash, i = 0; i < params[_length]; i++) {
                _domainHash = Pick(cookie, params[i] + domainHash, ";");
                if (!IsEmpty(_domainHash)) {
                    if (params[i] == _param_hutmv) {
                        _domainHash = _domainHash[_split](domainHash + ".")[1][_split]("|")[0];
                        if (IsEmpty(_domainHash)) {
                            continue
                        }
                        _domainHash = domainHash + "." + _domainHash
                    }
                    HPush(_params_hash, params[i] + _domainHash + ";")
                }
            }
            return Encode(_params_hash[_join]("+"))
        }
        ;
    }
    ;
    var GaEComm = function() {
        var oThis = this;
        oThis.transactions = [];
        oThis.addItem = function(orderId) {
            for (var _tran, _trans = oThis.transactions, i = 0; i < _trans[_length]; i++) {
                _tran = orderId == _trans[i].orderId ? _trans[i] : _tran
            }
            return _tran
        }
        ;
        oThis.addTrans = function(orderId, affiliation, total, tax, shipping, city, state, country) {
            var transaction = oThis.addItem(orderId);
            if (_undefined == transaction) {
                transaction = new GaEComm.Transaction(orderId,affiliation,total,tax,shipping,city,state,country);
                HPush(oThis.transactions, transaction)
            } else {
                transaction.affiliation = affiliation;
                transaction.total = total;
                transaction.tax = tax;
                transaction.shipping = shipping;
                transaction.city = city;
                transaction.state = state;
                transaction.country = country
            }
            return transaction
        }
    }
    ;
    GaEComm.Item = function(orderId, sku, name, category, price, quantity) {
        var oThis = this;
        oThis.orderId = orderId;
        oThis.sku = sku;
        oThis.name = name;
        oThis.category = category;
        oThis.price = price;
        oThis.quantity = quantity;
        oThis.serialize = function() {
            return "&" + ["hutmt=item", "tid=" + Encode(oThis.orderId), "ipc=" + Encode(oThis.sku), "ipn=" + Encode(oThis.name), "iva=" + Encode(oThis.category), "ipr=" + Encode(oThis.price), "iqt=" + Encode(oThis.quantity)][_join]("&hutm")
        }
    }
    ;
    GaEComm.Transaction = function(orderId, affiliation, total, tax, shipping, city, state, country) {
        var oThis = this;
        oThis.orderId = orderId;
        oThis.affiliation = affiliation;
        oThis.total = total;
        oThis.tax = tax;
        oThis.shipping = shipping;
        oThis.city = city;
        oThis.state = state;
        oThis.country = country;
        oThis.items = [];
        oThis.addItem = function(sku, name, category, price, quantity) {
            var item = oThis.getItem(sku)
              , orderId = oThis.orderId;
            if (_undefined == item) {
                HPush(oThis.items, new GaEComm.Item(orderId,sku,name,category,price,quantity))
            } else {
                item.orderId = orderId;
                item.sku = sku;
                item.name = name;
                item.category = category;
                item.price = price;
                item.quantity = quantity
            }
        }
        ;
        oThis.getItem = function(sku) {
            for (var item, _items = oThis.items, i = 0; i < _items[_length]; i++) {
                item = sku == _items[i].sku ? _items[i] : item
            }
            return item
        }
        ;
        oThis.serialize = function() {
            return "&" + ["hutmt=tran", "id=" + Encode(oThis.orderId), "st=" + Encode(oThis.affiliation), "to=" + Encode(oThis.total), "tx=" + Encode(oThis.tax), "sp=" + Encode(oThis.shipping), "ci=" + Encode(oThis.city), "rg=" + Encode(oThis.state), "co=" + Encode(oThis.country)][_join]("&hutmt")
        }
    }
    ;
    var HClient = function(detectFlash) {
        function getFlashVersion() {
            var _active_x_object, _flash, _version;
            _flash = "ShockwaveFlash";
            var _version_key = "$version"
              , navigator = $HGlobal[_str_window].navigator;
            if ((navigator = navigator ? navigator.plugins : _undefined) && navigator[_length] > 0) {
                for (var i = 0; i < navigator[_length] && !_version; i++) {
                    _flash = navigator[i];
                    if (Contains(_flash.name, "Shockwave Flash")) {
                        _version = _flash.description[_split]("Shockwave Flash ")[1]
                    }
                }
            } else {
                _flash = _flash + "." + _flash;
                try {
                    _active_x_object = new ActiveXObject(_flash + ".7");
                    _version = _active_x_object.GetVariable(_version_key)
                } catch (ex) {}
                if (!_version) {
                    try {
                        _active_x_object = new ActiveXObject(_flash + ".6");
                        _version = "WIN 6,0,21,0";
                        _active_x_object.$_version = "always";
                        _version = _active_x_object.GetVariable(_version_key)
                    } catch (ex) {}
                }
                if (!_version) {
                    try {
                        _active_x_object = new ActiveXObject(_flash);
                        _version = _active_x_object.GetVariable(_version_key)
                    } catch (ex) {}
                }
                if (_version) {
                    _version = _version[_split](" ")[1][_split](",");
                    _version = _version[0] + "." + _version[1] + " r" + _version[2]
                }
            }
            return _version ? _version : _empty
        }
        var oThis = this
          , _empty = "-"
          , screen = $HGlobal[_str_window].screen
          , navigator = $HGlobal[_str_window].navigator;
        oThis.screen = screen ? screen.width + "x" + screen.height : _empty;
        oThis.colorDepth = screen ? screen.colorDepth + "-bit" : _empty;
        oThis.charset = Encode($HGlobal[_str_document].characterSet ? $HGlobal[_str_document].characterSet : $HGlobal[_str_document].charset ? $HGlobal[_str_document].charset : _empty);
        oThis.language = (navigator && navigator.language ? navigator.language : navigator && navigator.browserLanguage ? navigator.browserLanguage : _empty)[_toLowerCase]();
        oThis.javaEnabled = navigator && navigator.javaEnabled() ? 1 : 0;
        oThis.flashVersion = detectFlash ? getFlashVersion() : _empty;
        oThis.getClientInfo = function() {
            return _amp_ + "hutm" + ["cs=" + Encode(oThis.charset), "sr=" + oThis.screen, "sc=" + oThis.colorDepth, "ul=" + oThis.language, "je=" + oThis.javaEnabled, "fl=" + Encode(oThis.flashVersion)][_join]("&hutm")
        }
        ;
        oThis.hashClientInfo = function() {
            var navigator = $HGlobal[_str_window].navigator
              , history_length = $HGlobal[_str_window].history[_length];
            navigator = navigator.appName + navigator.version + oThis.language + navigator.platform + navigator.userAgent + oThis.javaEnabled + oThis.screen + oThis.colorDepth + ($HGlobal[_str_document][_cookie] ? $HGlobal[_str_document][_cookie] : "") + ($HGlobal[_str_document].referrer ? $HGlobal[_str_document].referrer : "");
            for (var len = navigator[_length]; history_length > 0; ) {
                navigator += history_length-- ^ len++
            }
            return Hash(navigator)
        }
    }
    ;
    var HSourceTracker = function(domainHash, processedSource, timestamp, config) {
        function getDomain(url) {
            var _domain = "";
            _domain = url[_split]("://")[1][_toLowerCase]();
            if (Contains(_domain, "/")) {
                _domain = _domain[_split]("/")[0]
            }
            return _domain
        }
        var _config = config
          , oThis = this;
        oThis.domainHash = domainHash;
        oThis.processedSource = processedSource;
        oThis.timestamp = timestamp;
        oThis.createOrganicSource = function(url) {
            var organic = oThis.getOrganicSource();
            return new HSourceTracker.HSource(Pick(url, _config.id + _equal_, _amp_),Pick(url, _config.source + _equal_, _amp_),Pick(url, _config.clid + _equal_, _amp_),oThis.getValue(url, _config.campaign, "(not set)"),oThis.getValue(url, _config.medium, "(not set)"),oThis.getValue(url, _config.term, organic && !IsEmpty(organic.G) ? Fa(organic.G) : _undefined),oThis.getValue(url, _config.content, _undefined))
        }
        ;
        oThis.isGoogleCustomSearch = function(url) {
            var _domain = getDomain(url), _url;
            _url = url;
            var path = "";
            _url = _url[_split]("://")[1][_toLowerCase]();
            if (Contains(_url, "/")) {
                _url = _url[_split]("/")[1];
                if (Contains(_url, "?")) {
                    path = _url[_split]("?")[0]
                }
            }
            _url = path;
            if (Contains(_domain, "google")) {
                url = url[_split]("?")[_join](_amp_);
                if (Contains(url, _amp_ + _config.googleKey + _equal_)) {
                    if (_url == _config.googleCustomSearchPath) {
                        return _true
                    }
                }
            }
            return _false
        }
        ;
        oThis.getOrganicSource = function() {
            var key, processedSource = oThis.processedSource, _organic, organic = _config.organic;
            if (!(IsEmpty(processedSource) || "0" == processedSource || !Contains(processedSource, "://") || oThis.isGoogleCustomSearch(processedSource))) {
                key = getDomain(processedSource);
                for (var i = 0; i < organic[_length]; i++) {
                    _organic = organic[i];
                    if (Contains(key, _organic.engine[_toLowerCase]())) {
                        processedSource = processedSource[_split]("?")[_join](_amp_);
                        if (Contains(processedSource, _amp_ + _organic.keyword + _equal_)) {
                            key = processedSource[_split](_amp_ + _organic.keyword + _equal_)[1];
                            if (Contains(key, _amp_)) {
                                key = key[_split](_amp_)[0]
                            }
                            return new HSourceTracker.HSource(_undefined,_organic.engine,_undefined,"(organic)","organic",key,_undefined)
                        }
                    }
                }
            }
        }
        ;
        oThis.getValue = function(map, key, _default) {
            var value = Pick(map, key + _equal_, _amp_)
              , result = "-";
            return result = !IsEmpty(value) ? Decode(value) : !IsEmpty(_default) ? _default : "-"
        }
        ;
        oThis.isIgnoredOrganic = function(source) {
            var ignoredOrganic = _config.ignoredOrganic
              , result = _false;
            if (source && "organic" == source.medium) {
                source = Decode(source.term)[_toLowerCase]();
                for (var i = 0; i < ignoredOrganic[_length]; i++) {
                    result = result || ignoredOrganic[i][_toLowerCase]() == source
                }
            }
            return result
        }
        ;
        oThis.getReferralSource = function() {
            var processedSource = ""
              , content = "";
            processedSource = oThis.processedSource;
            if (!(IsEmpty(processedSource) || "0" == processedSource || !Contains(processedSource, "://") || oThis.isGoogleCustomSearch(processedSource))) {
                processedSource = processedSource[_split]("://")[1];
                if (Contains(processedSource, "/")) {
                    content = processedSource[_substring](processedSource[_str_indexOf]("/"));
                    content = content[_split]("?")[0];
                    processedSource = processedSource[_split]("/")[0][_toLowerCase]()
                }
                if (0 == processedSource[_str_indexOf]("www.")) {
                    processedSource = processedSource[_substring](4)
                }
                return new HSourceTracker.HSource(_undefined,processedSource,_undefined,"(referral)","referral",_undefined,content)
            }
        }
        ;
        oThis.parseHasAnchorUrl = function(url) {
            var result = "";
            if (_config.allowAnchor) {
                result = url && url.hash ? url[_href][_substring](url[_href][_str_indexOf]("#")) : "";
                result = "" != result ? result + _amp_ : result
            }
            result += url.search;
            return result
        }
        ;
        oThis.getDirectSource = function() {
            return new HSourceTracker.HSource(_undefined,"(direct)",_undefined,"(direct)","(none)",_undefined,_undefined)
        }
        ;
        oThis.isIgnoredReferral = function(source) {
            var result = _false
              , ignoredReferral = _config.ignoredReferral;
            if (source && "referral" == source.medium) {
                source = Encode(source.source)[_toLowerCase]();
                for (var i = 0; i < ignoredReferral[_length]; i++) {
                    result = result || Contains(source, ignoredReferral[i][_toLowerCase]())
                }
            }
            return result
        }
        ;
        oThis.check = function(source) {
            return _undefined != source && source.checkSelfIntegrity()
        }
        ;
        oThis.checkUTMZ = function(cookie) {
            var _hutmz = Pick(cookie, _param_hutmz + oThis.domainHash + ".", ";");
            var arrUTMZ = _hutmz[_split](".");
            var _b_source = new HSourceTracker.HSource;
            _b_source.initialize(arrUTMZ.slice(4)[_join]("."));
            if (!oThis.check(_b_source)) {
                return _true
            }
            var url = $HGlobal[_str_document][_location];
            url = oThis.parseHasAnchorUrl(url);
            var _a_source = oThis.createOrganicSource(url);
            if (!oThis.check(_a_source)) {
                _a_source = oThis.getOrganicSource();
                oThis.check(_a_source) || (_a_source = oThis.getReferralSource())
            }
            return oThis.check(_a_source) && _b_source.serialize()[_toLowerCase]() != _a_source.serialize()[_toLowerCase]()
        }
        ;
        oThis.dc = function(gaCookie, c) {
            if (_config.campaignTrack) {
                var url = "", _hutmz = "-", _source, q = 0, b, cookie, _domainHash = oThis.domainHash;
                if (gaCookie) {
                    cookie = gaCookie.getCookie();
                    url = oThis.parseHasAnchorUrl($HGlobal[_str_document][_location]);
                    if (_config.allowLinker && gaCookie.checkParamsHash()) {
                        _hutmz = gaCookie.getUTMZ();
                        if (!IsEmpty(_hutmz) && !Contains(_hutmz, ";")) {
                            gaCookie.setUTMZCookie();
                            return
                        }
                    }
                    _hutmz = Pick(cookie, _param_hutmz + _domainHash + ".", ";");
                    _source = oThis.createOrganicSource(url);
                    if (oThis.check(_source)) {
                        url = Pick(url, _config.nooverride + _equal_, _amp_);
                        if ("1" == url && !IsEmpty(_hutmz)) {
                            return
                        }
                    }
                    if (!oThis.check(_source)) {
                        _source = oThis.getOrganicSource();
                        url = oThis.isIgnoredOrganic(_source);
                        if (!IsEmpty(_hutmz) && url) {
                            return
                        }
                        if (url) {
                            _source = oThis.getDirectSource()
                        }
                    }
                    if (!oThis.check(_source) && c) {
                        _source = oThis.getReferralSource();
                        url = oThis.isIgnoredReferral(_source);
                        if (!IsEmpty(_hutmz) && url) {
                            return
                        }
                        if (url) {
                            _source = oThis.getDirectSource()
                        }
                    }
                    if (!oThis.check(_source)) {
                        if (IsEmpty(_hutmz) && c) {
                            _source = oThis.getDirectSource()
                        }
                    }
                    if (oThis.check(_source)) {
                        if (!IsEmpty(_hutmz)) {
                            q = _hutmz[_split](".");
                            b = new HSourceTracker.HSource;
                            b.initialize(q.slice(4)[_join]("."));
                            b = b.serialize()[_toLowerCase]() == _source.serialize()[_toLowerCase]();
                            q = q[3] * 1
                        }
                        if (!b || c) {
                            cookie = Pick(cookie, _param_hutma + _domainHash + ".", ";");
                            b = cookie.lastIndexOf(".");
                            cookie = b > 9 ? cookie[_substring](b + 1) * 1 : 0;
                            q++;
                            cookie = 0 == cookie ? 1 : cookie;
                            gaCookie.setUTMZ([_domainHash, oThis.timestamp, cookie, q, _source.serialize()][_join]("."));
                            gaCookie.setUTMZCookie()
                        }
                    }
                }
            }
        }
    }
    ;
    HSourceTracker.HSource = function(id, source, clid, campaign, medium, term, content) {
        var oThis = this;
        oThis.id = id;
        oThis.source = source;
        oThis.clid = clid;
        oThis.campaign = campaign;
        oThis.medium = medium;
        oThis.term = term;
        oThis.content = content;
        oThis.serialize = function() {
            var source = [], map = [["cid", oThis.id], ["csr", oThis.source], ["gclid", oThis.clid], ["ccn", oThis.campaign], ["cmd", oThis.medium], ["ctr", oThis.term], ["cct", oThis.content]], i, value;
            if (oThis.checkSelfIntegrity()) {
                for (i = 0; i < map[_length]; i++) {
                    if (!IsEmpty(map[i][1])) {
                        value = map[i][1][_split]("+")[_join]("%20");
                        value = value[_split](" ")[_join]("%20");
                        HPush(source, "hutm" + map[i][0] + _equal_ + value)
                    }
                }
            }
            return $HGlobal.processSource(source[_join]("|"))
        }
        ;
        oThis.checkSelfIntegrity = function() {
            return !(IsEmpty(oThis.id) && IsEmpty(oThis.source) && IsEmpty(oThis.clid))
        }
        ;
        oThis.initialize = function(map) {
            var _get = function(key) {
                return Decode(Pick(map, "hutm" + key + _equal_, "|"))
            }
            ;
            oThis.id = _get("cid");
            oThis.source = _get("csr");
            oThis.clid = _get("gclid");
            oThis.campaign = _get("ccn");
            oThis.medium = _get("cmd");
            oThis.term = _get("ctr");
            oThis.content = _get("cct")
        }
    }
    ;
    var HCustomVariable = function(config, domainHash, gaCookie, cache) {
        var oThis = this
          , _domainHash = domainHash
          , _equal = _equal_
          , _config = config
          , _cache = cache;
        oThis.gaCookie = gaCookie;
        oThis.variable = "";
        oThis.customVariables = {};
        oThis.initialize = function() {
            var _variables;
            _variables = Pick(oThis.gaCookie.getCookie(), _param_hutmv + _domainHash + ".", ";")[_split](_domainHash + ".")[1];
            if (!IsEmpty(_variables)) {
                _variables = _variables[_split]("|");
                var _customVariables = oThis.customVariables, _var_1 = _variables[1], _variable;
                if (!IsEmpty(_var_1)) {
                    _var_1 = _var_1[_split](",");
                    for (var i = 0; i < _var_1[_length]; i++) {
                        _variable = _var_1[i];
                        if (!IsEmpty(_variable)) {
                            _variable = _variable[_split](_equal);
                            if (_variable[_length] == 4) {
                                _customVariables[_variable[0]] = [_variable[1], _variable[2], 1]
                            }
                        }
                    }
                }
                oThis.variable = _variables[0];
                oThis.save()
            }
        }
        ;
        oThis.save = function() {
            oThis.clearCache();
            var _variable = oThis.variable, customVariable, d, q = "";
            for (customVariable in oThis.customVariables) {
                if ((d = oThis.customVariables[customVariable]) && 1 === d[2]) {
                    q += customVariable + _equal + d[0] + _equal + d[1] + _equal + 1 + ",";
                    IsEmpty(q) || (_variable += "|" + q)
                }
            }
            if (IsEmpty(_variable)) {
                oThis.gaCookie.deleteUTMVCookie()
            } else {
                oThis.gaCookie.setUTMV(_domainHash + "." + _variable);
                oThis.gaCookie.setUTMVCookie()
            }
        }
        ;
        oThis.setVar = function(variable) {
            oThis.variable = variable;
            oThis.save()
        }
        ;
        oThis.setCustomVar = function(index, name, value, scope) {
            if (1 != scope && 2 != scope && 3 != scope) {
                scope = 3
            }
            var result = _false;
            if (name && value && index > 0 && index <= _config.maxCustomVariables) {
                name = Encode(name);
                value = Encode(value);
                if (name[_length] + value[_length] <= 64) {
                    oThis.customVariables[index] = [name, value, scope];
                    oThis.save();
                    result = _true
                }
            }
            return result
        }
        ;
        oThis.getVisitorCustomVar = function(index) {
            var customVariable;
            if ((customVariable = oThis.customVariables[index]) && 1 === customVariable[2]) {
                return customVariable[1]
            }
        }
        ;
        oThis.deleteCustomVar = function(index) {
            var _customVariables = oThis.customVariables;
            if (_customVariables[index]) {
                delete _customVariables[index];
                oThis.save()
            }
        }
        ;
        oThis.clearCache = function() {
            _cache.clearKey(8);
            _cache.clearKey(9);
            _cache.clearKey(11);
            var _customVariables = oThis.customVariables, _customVariable, key;
            for (key in _customVariables) {
                if (_customVariable = _customVariables[key]) {
                    _cache.setKey(8, key, _customVariable[0]);
                    _cache.setKey(9, key, _customVariable[1]);
                    (_customVariable = _customVariable[2]) && 3 != _customVariable && _cache.setKey(11, key, "" + _customVariable)
                }
            }
        }
    }
    ;
    var HCache = function() {
        function _set(cache, key, idx, vlaue) {
            if (_undefined == _Cache[cache]) {
                _Cache[cache] = {}
            }
            if (_undefined == _Cache[cache][key]) {
                _Cache[cache][key] = []
            }
            _Cache[cache][key][idx] = vlaue
        }
        function _get(cache, key, idx) {
            if (_undefined != _Cache[cache] && _undefined != _Cache[cache][key]) {
                return _Cache[cache][key][idx]
            }
        }
        function _clear(cache, key) {
            if (_undefined != _Cache[cache] && _undefined != _Cache[cache][key]) {
                _Cache[cache][key] = _undefined;
                var y = _true, i;
                for (i = 0; i < _map_key[_length]; i++) {
                    if (_undefined != _Cache[cache][_map_key[i]]) {
                        y = _false;
                        break
                    }
                }
                if (y) {
                    _Cache[cache] = _undefined
                }
            }
        }
        function _serialize(o) {
            var serialization = "", y = _false, i, _key;
            for (i = 0; i < _map_key[_length]; i++) {
                _key = o[_map_key[i]];
                if (_undefined != _key) {
                    if (y) {
                        serialization += _map_key[i]
                    }
                    y = [];
                    var J = void 0
                      , G = void 0;
                    for (G = 0; G < _key[_length]; G++) {
                        if (_undefined != _key[G]) {
                            J = "";
                            if (G != t && _undefined == _key[G - 1]) {
                                J += G[_toString]() + _exclamation
                            }
                            var R;
                            R = _key[G];
                            var ma = ""
                              , Y = void 0
                              , da = void 0
                              , Ga = void 0;
                            for (Y = 0; Y < R[_length]; Y++) {
                                da = R[_charAt](Y);
                                Ga = _special_sign[da];
                                ma += _undefined != Ga ? Ga : da
                            }
                            R = ma;
                            J += R;
                            HPush(y, J)
                        }
                    }
                    _key = _left_parenthesis + y[_join](_asterisk) + _right_parenthesis;
                    serialization += _key;
                    y = _false
                } else {
                    y = _true
                }
            }
            return serialization
        }
        var oThis = this
          , bind = HCreateBinder(oThis)
          , _Cache = {}
          , _key = "k"
          , _value = "v"
          , _map_key = [_key, _value]
          , _left_parenthesis = "("
          , _right_parenthesis = ")"
          , _asterisk = "*"
          , _exclamation = "!"
          , _quote = "'"
          , _special_sign = {};
        _special_sign[_quote] = "'0";
        _special_sign[_right_parenthesis] = "'1";
        _special_sign[_asterisk] = "'2";
        _special_sign[_exclamation] = "'3";
        var t = 1;
        oThis.exist = function(cache) {
            return _undefined != _Cache[cache]
        }
        ;
        oThis.serialize = function() {
            var serialization = "", cache;
            for (cache in _Cache) {
                if (_undefined != _Cache[cache]) {
                    serialization += cache[_toString]() + _serialize(_Cache[cache])
                }
            }
            return serialization
        }
        ;
        oThis.Serialize = function(cache) {
            if (cache == _undefined) {
                return oThis.serialize()
            }
            var serialization = cache.serialize(), key;
            for (key in _Cache) {
                if (_undefined != _Cache[key] && !cache.exist(key)) {
                    serialization += key[_toString]() + _serialize(_Cache[key])
                }
            }
            return serialization
        }
        ;
        oThis.setKey = bind("_setKey", 89, function(cache, idx, key) {
            if (typeof key != "string") {
                return _false
            }
            _set(cache, _key, idx, key);
            return _true
        });
        oThis.setValue = bind("_setValue", 90, function(cache, idx, value) {
            if (typeof value != "number" && (_undefined == Number || !(value instanceof Number)) || _o_math.round(value) != value || value == NaN || value == Infinity) {
                return _false
            }
            _set(cache, _value, idx, value[_toString]());
            return _true
        });
        oThis.getKey = bind("_getKey", 87, function(cache, idx) {
            return _get(cache, _key, idx)
        });
        oThis.getValue = bind("_getValue", 88, function(cache, idx) {
            return _get(cache, _value, idx)
        });
        oThis.clearKey = bind("_clearKey", 85, function(cache) {
            _clear(cache, _key)
        });
        oThis.clearValue = bind("_clearValue", 86, function(cache) {
            _clear(cache, _value)
        })
    }
    ;
    var EventTracker = function(category, tracker) {
        var oThis = this
          , bind = HCreateBinder(oThis);
        oThis.tracker = tracker;
        oThis.category = category;
        oThis.trackEvent = bind("_trackEvent", 91, function(action, label, value) {
            return tracker.trackEvent(oThis.category, action, label, value)
        })
    }
    ;
    var HTimeTracker = function(tracker, gaAjax) {
        var oThis = this
          , external = $HGlobal[_str_window].external
          , webkitPerformance = $HGlobal[_str_window].webkitPerformance
          , _trackTimeRate = 10;
        oThis.cache = new HCache;
        oThis.getLoadTime = function() {
            var i, _timing = "timing", _onloadT = "onloadT";
            if (external && external[_onloadT] != _undefined && external.isValidLoadTime) {
                i = external[_onloadT]
            } else {
                if (webkitPerformance && webkitPerformance[_timing]) {
                    i = webkitPerformance[_timing].loadEventStart - webkitPerformance[_timing].navigationStart
                }
            }
            return i
        }
        ;
        oThis.isTrackTime = function() {
            return tracker.isSample() && tracker.visitCode() % 100 < _trackTimeRate
        }
        ;
        oThis.send = function() {
            var param = "&hutmt=event&hutme=" + Encode(oThis.cache.serialize()) + tracker.getPageInfo();
            gaAjax.send(param, tracker.account, tracker.domainHash, _false, _true)
        }
        ;
        oThis.trackLoadTime = function() {
            var _loadTime = oThis.getLoadTime();
            if (_loadTime == _undefined) {
                return _false
            }
            if (_loadTime <= 0) {
                return _true
            }
            if (_loadTime > 2147483648) {
                return _false
            }
            var _cache = oThis.cache;
            _cache.clearKey(14);
            _cache.clearValue(14);
            _cache.setValue(14, 1, _loadTime) && oThis.send();
            external && external.isValidLoadTime != _undefined && external.setPageReadyTime();
            return _false
        }
        ;
        oThis.trackPageLoadTime = function() {
            if (!oThis.isTrackTime()) {
                return _false
            }
            if ($HGlobal[_str_window].top != $HGlobal[_str_window]) {
                return _false
            }
            oThis.trackLoadTime() && AddListener($HGlobal[_str_window], "load", oThis.trackLoadTime, _false);
            return _true
        }
    }
    ;
    var GASO = function() {}
    ;
    GASO.getGASO = function(gaCookie) {
        var _gaso = "gaso="
          , result = ""
          , url = $HGlobal[_str_document][_location].hash;
        if (url && 1 == url[_str_indexOf](_gaso)) {
            result = Pick(url, _gaso, _amp_)
        } else {
            result = (url = $HGlobal[_str_window].name) && 0 <= url[_str_indexOf](_gaso) ? Pick(url, _gaso, _amp_) : Pick(gaCookie.getCookie(), _param_gaso, ";")
        }
        return result
    }
    ;
    GASO.create = function(gaso, domain) {
        var src = (domain || "www") + ".google.com";
        src = "https://" + src + "/analytics/reporting/overlay_js?gaso=" + gaso + _amp_ + Random();
        var id = "_gasojs"
          , script = $HGlobal[_str_document].createElement("script");
        script.type = "text/javascript";
        script.src = src;
        if (id) {
            script.id = id
        }
        ($HGlobal[_str_document].getElementsByTagName("head")[0] || $HGlobal[_str_document].getElementsByTagName("body")[0]).appendChild(script)
    }
    ;
    GASO.load = function(config, gaCookie) {
        if (!GASO.created) {
            var _gaso = GASO.getGASO(gaCookie)
              , arr_gaso = _gaso && _gaso.match(/^(?:\|([-0-9a-z.]{1,30})\|)?([-.\w]{10,1200})$/i);
            if (arr_gaso) {
                gaCookie.setGASO(_gaso);
                gaCookie.setGASOCookie();
                _HTrackerFactory._gasoDomain = config.domain;
                _HTrackerFactory._gasoCPath = config.cookiePath;
                GASO.create(arr_gaso[2], arr_gaso[1])
            }
            GASO.created = _true
        }
    }
    ;
    var HTracker = function(name, account, namespace) {
        function _getDomain() {
            if ("auto" == config.domain) {
                var domain = $HGlobal[_str_document].domain;
                if ("www." == domain[_substring](0, 4)) {
                    domain = domain[_substring](4)
                }
                config.domain = domain
            }
            config.domain = config.domain[_toLowerCase]()
        }
        function _notGoogleDomain() {
            _getDomain();
            var domain = config.domain
              , _isGoogle = domain[_str_indexOf]("www.google.") * domain[_str_indexOf](".google.") * domain[_str_indexOf]("google.");
            return _isGoogle || ("/" != config.cookiePath) || (domain[_str_indexOf]("google.org") > -1)
        }
        function _setMapTimestamp(map, separator, timestamp) {
            if (IsEmpty(map) || IsEmpty(separator) || IsEmpty(timestamp)) {
                return "-"
            }
            map = Pick(map, _param_hutma + oThis.domainHash + ".", separator);
            if (!IsEmpty(map)) {
                map = map[_split](".");
                map[5] = "" + (map[5] ? map[5] * 1 + 1 : 1);
                map[3] = map[4];
                map[4] = timestamp;
                map = map[_join](".")
            }
            return map
        }
        function _isIgnoreUrl() {
            return ("file:" != $HGlobal[_str_document][_location][_protocol]) && _notGoogleDomain()
        }
        var oThis = this
          , bind = HCreateBinder(oThis)
          , gaAjax = _undefined
          , config = new Config
          , d = _false
          , customVariable = _undefined,
          uuid = new UUID(),
          paramUtil = new localParamDataUtil()
          ;
        oThis.name = name;
        oThis.timestamp = _o_math.round((new Date)[_getTime]() / 1000);
        oThis.account = account || "UA-XXXXX-YY";
        oThis.referrer = $HGlobal[_str_document].referrer;
        oThis.processedSource = _undefined;
        oThis.transactions = _undefined;
        oThis.A = _false;
        oThis.client = _undefined;
        oThis.cache = _undefined;
        oThis.eventCache = _undefined;
        oThis.pageLoadTimeTracker = _undefined;
        oThis.domainHash = _undefined;
        oThis.gaCookie = _undefined;
        config.namespace = namespace ? Encode(namespace) : _undefined;
        var pcookie = new PCookie(config)
        ,zeroTagMap = new  zeroChannelTagMap();
        oThis.getVisitorId = function() {//可参考
            return Random() ^ oThis.client.hashClientInfo() & 2147483647
        };
        //创建uuid
        oThis.createVisitCode=function() {
        	return uuid.uuid(null, null);
        };
        oThis.getVisitCode=function() {
        	return pcookie.getVisitCookie();
        };
        oThis.writePageIdCookie = function(){
        	var  pageid = paramUtil.getPageId();
        	pcookie.setPageIdCookie(pageid);
        };
        oThis.getDomainHash = function() {
            if (!config.domain || "" == config.domain || "none" == config.domain) {
                config.domain = "";
                return 1
            }
            _getDomain();
            return config.allowHash ? Hash(config.domain) : 1
        }
        ;
        oThis.processReferrer = function(referrer, domain) {
            if (IsEmpty(referrer)) {
                referrer = "-"
            } else {
                domain += (config.cookiePath && "/" != config.cookiePath) ? config.cookiePath : "";
                var n = referrer[_str_indexOf](domain);
                referrer = (n >= 0 && n <= 12) ? "0" : (("[" == referrer[_charAt](0)) && ("]" == referrer[_charAt](referrer[_length] - 1))) ? "-" : referrer
            }
            return referrer
        }
        ;
        oThis.getPageInfo = function(path) {
            var param = "";
            param += config.clientInfo ? oThis.client.getClientInfo() : "";
            param += (config.detectTitle && !IsEmpty($HGlobal[_str_document].title)) ? "&hutmdt=" + Encode($HGlobal[_str_document].title) : "";
            var hid;
            hid = _undefined;
            if ($HGlobal[_str_window] && $HGlobal[_str_window][_gaGlobal] && $HGlobal[_str_window][_gaGlobal].hid) {
                hid = $HGlobal[_str_window][_gaGlobal].hid
            } else {
                hid = Random();
                $HGlobal[_str_window].gaGlobal = $HGlobal[_str_window][_gaGlobal] ? $HGlobal[_str_window][_gaGlobal] : {};
                $HGlobal[_str_window][_gaGlobal].hid = hid
            }
            param += "&hutmhid=" + hid + "&hutmr=" + Encode(_o_string(oThis.processedSource)) + "&hutmp=" + Encode(oThis.processPath(path));
            param += "&hutmos=" + BrowserDetect.OS + "&hutmbro=" + BrowserDetect.browser + "" + BrowserDetect.version + "";
            var page_end = new Date();
            var duration = -1;
            if (typeof (_head_start) != "undefined") {
                duration = page_end - _head_start
            }
            param += "&hutmdur=" + duration;
            param += "&hutmref=" + Encode(oThis.referrer);
            return param
        }
        ;
        oThis.processPath = function(path) {
            var location = $HGlobal[_str_document][_location];
            path && HRegister(13);
            return path = ((undefined != path) && ("" != path)) ? Encode(path, _true) : Encode(location.pathname + location.search, _true)
        }
        ;
        oThis.send = function(path) {
            if (oThis.isSample()) {
                var param = "";
                if (oThis.cache != _undefined && oThis.cache.serialize()[_length] > 0) {
                    param += "&hutme=" + Encode(oThis.cache.serialize())
                }
                param += oThis.getPageInfo(path);
                gaAjax.send(param, oThis.account, oThis.domainHash)
            }
        }
        ;
        oThis.writeVisitCookie = function(){
        	var visit = oThis.getVisitCode()||oThis.createVisitCode();
        	pcookie.setVisitCookie(visit);
        };
        
        oThis.getParams = function() {
            var _gaCookie = new HaCookie(config);
            return _gaCookie.setDomainHashByCookie(oThis.domainHash) ? _gaCookie.getParams() : _undefined
        };
        oThis.getLinkerUrl = bind("_getLinkerUrl", 52, function(targetUrl, useHash) {
            var n = targetUrl[_split]("#")
              , _targetUrl = targetUrl
              , params = oThis.getParams();
            if (params) {
                if (useHash && (1 >= n[_length])) {
                    _targetUrl += "#" + params
                }
            } else {
                if (!useHash || (1 >= n[_length])) {
                    if (1 >= n[_length]) {
                        _targetUrl += (Contains(targetUrl, "?") ? _amp_ : "?") + params
                    } else {
                        _targetUrl = n[0] + (Contains(targetUrl, "?") ? _amp_ : "?") + params + "#" + n[1]
                    }
                }
            }
            return _targetUrl
        });
        oThis.rc = function() {
            var timestamp = oThis.timestamp, _gaCookie = oThis.gaCookie, cookie = _gaCookie.getCookie(), domainHash = oThis.domainHash + "", gaGlobal = $HGlobal[_str_window] ? $HGlobal[_str_window][_gaGlobal] : _undefined, _anchor, _has_hutma = Contains(cookie, _param_hutma + domainHash + "."), _has_hutmb = Contains(cookie, _param_hutmb + domainHash), _has_hutmc = Contains(cookie, _param_hutmc + domainHash), _hutma, _hutmb = [], param = "", ma = _false;
            cookie = IsEmpty(cookie) ? "" : cookie;
            if (config.allowLinker) {
                _anchor = ($HGlobal[_str_document][_location] && $HGlobal[_str_document][_location].hash) ? $HGlobal[_str_document][_location][_href][_substring]($HGlobal[_str_document][_location][_href][_str_indexOf]("#")) : "";
                if (config.allowAnchor && !IsEmpty(_anchor)) {
                    param = _anchor + _amp_
                }
                param += $HGlobal[_str_document][_location].search;
                if (!IsEmpty(param) && Contains(param, _param_hutma)) {
                    _gaCookie.setUTMKHashByLocation(param);
                    _gaCookie.cb() || _gaCookie.initialize();
                    _hutma = _gaCookie.getUTMA()
                }
                var _getUTMX = _gaCookie.getUTMX;
                var _setUTMX = _gaCookie.setUTMX;
                var _setUTMXCookie = _gaCookie.setUTMXCookie;
                if (!IsEmpty(_getUTMX())) {
                    _setUTMX(Decode(_getUTMX()));
                    Contains(_getUTMX(), ";") || _setUTMXCookie()
                }
                var _getUTMV = _gaCookie.getUTMV;
                var _setUTMV = _gaCookie.setUTMV;
                var _setUTMVCookie = _gaCookie.setUTMVCookie;
                if (!IsEmpty(_getUTMV())) {
                    _setUTMV(_getUTMV());
                    Contains(_getUTMV(), ";") || _setUTMVCookie()
                }
            }
            if (IsEmpty(_hutma)) {
                if (_has_hutma) {
                    if (_hutma = (!_has_hutmb || !_has_hutmc)) {
                        _hutma = _setMapTimestamp(cookie, ";", _o_string(timestamp));
                        oThis.A = _true
                    } else {
                        _hutma = Pick(cookie, _param_hutma + domainHash + ".", ";");
                        _hutmb = Pick(cookie, _param_hutmb + domainHash, ";")[_split](".")
                    }
                } else {
                    _hutma = [domainHash, oThis.getVisitorId(), timestamp, timestamp, timestamp, 1][_join](".");
                    ma = oThis.A = _true
                }
            } else {
                if (IsEmpty(_gaCookie.getUTMB()) || IsEmpty(_gaCookie.getUTMC())) {
                    _hutma = _setMapTimestamp(param, _amp_, _o_string(timestamp));
                    oThis.A = _true
                } else {
                    _hutmb = _gaCookie.getUTMB()[_split](".");
                    domainHash = _hutmb[0]
                }
            }
            _hutma = _hutma[_split](".");
            if ($HGlobal[_str_window] && gaGlobal && gaGlobal.dh == domainHash && !config.namespace) {
                _hutma[4] = gaGlobal.sid ? gaGlobal.sid : _hutma[4];
                if (ma) {
                    _hutma[3] = gaGlobal.sid ? gaGlobal.sid : _hutma[4];
                    if (gaGlobal.vid) {
                        timestamp = gaGlobal.vid[_split](".");
                        _hutma[1] = timestamp[0];
                        _hutma[2] = timestamp[1]
                    }
                }
            }
            _gaCookie.setUTMA(_hutma[_join]("."));
            _hutmb[0] = domainHash;
            _hutmb[1] = _hutmb[1] ? _hutmb[1] : 0;
            _hutmb[2] = _undefined != _hutmb[2] ? _hutmb[2] : config.Sc;
            _hutmb[3] = _hutmb[3] ? _hutmb[3] : _hutma[4];
            _gaCookie.setUTMB(_hutmb[_join]("."));
            _gaCookie.setUTMC(domainHash);
            IsEmpty(_gaCookie.getUTMParamsHash()) || _gaCookie.setUTMParamsHash(_gaCookie.hashUTMParams());
            _gaCookie.setUTMACookie();
            _gaCookie.setUTMBCookie();
            _gaCookie.setUTMCCookie()
        }
        ;
        oThis.setGaAjax = function() {
            gaAjax = new GaAjax(config)
        }
        ;
        oThis.getName = bind("_getName", 58, function() {
            return oThis.name
        });
        oThis.initData = bind("_initData", 2, function() {
            var sourceTracker;
            if (!d) {
                if (!oThis.client) {
                    oThis.client = new HClient(config.detectFlash)
                }
                oThis.domainHash = oThis.getDomainHash();
                oThis.gaCookie = new HaCookie(config);
                oThis.cache = new HCache;
                customVariable = new HCustomVariable(config,_o_string(oThis.domainHash),oThis.gaCookie,oThis.cache);
                oThis.setGaAjax()
            }
            if (_isIgnoreUrl()) {
                if (!d) {
                    oThis.processedSource = oThis.processReferrer(oThis.referrer, $HGlobal[_str_document].domain);
                    sourceTracker = new HSourceTracker(_o_string(oThis.domainHash),oThis.processedSource,oThis.timestamp,config)
                }
                oThis.rc(sourceTracker);
                customVariable.initialize()
            }
            if (!d) {
                _isIgnoreUrl() && sourceTracker.dc(oThis.gaCookie, oThis.A);
                oThis.eventCache = new HCache;
                GASO.load(config, oThis.gaCookie);
                d = _true
            }
        });
        oThis.visitCode = bind("_visitCode", 54, function() {
            oThis.initData();
            var _hutma = Pick(oThis.gaCookie.getCookie(), _param_hutma + oThis.domainHash + ".", ";");
            _hutma = _hutma[_split](".");
            return _hutma[_length] < 4 ? "" : _hutma[1]
        });
        oThis.cookiePathCopy = bind("_cookiePathCopy", 30, function(newPath) {
            oThis.initData();
            oThis.gaCookie && oThis.gaCookie.copyCookiePath(oThis.domainHash, newPath)
        });
        oThis.isSample = function() {
            return oThis.visitCode() % 10000 < config.sampleRate * 100
        }
        ;
        oThis.hasOrNotHtagDo=function(){
        	var $htag = paramUtil.getHtag();
        	if($htag)oThis.doHasHtag($htag);
        	else oThis.doHasNoHtag();
        };
        oThis.getOtrackParam = function(otrack, htag){
        	if(htag){
        		try {
        		  if(typeof htag =="string"){
        			var ha = htag.split(".");
        			if(ha && ha.length==2){
        				var pageLevel = parseInt(ha[0]);
        				var suffix = ha[1];
        				if(!otrack){
        					var ret = new Array("0","0","0","0","0");
        					ret[pageLevel] = suffix;
        					ret[ret.length-1] = pageLevel+"";
        					return ret;
        				}else{
        					var dot = otrack.split(".");
        					var predotArr = dot[0].split("-");
        					predotArr[pageLevel] = suffix;
        					for(var i = pageLevel + 1; i < predotArr.length; i++){
        						predotArr[i] = "0";
        					}
        					var newArr = predotArr.slice();
        					newArr.push(pageLevel+"");
        					return newArr;
        				}
        			}
        		 }
				} catch (e) {
					return new Array("9","9","9","9","0");
				}
        	}
        };
        oThis.formatOtrack=function(otrackParamArray){
        	if(otrackParamArray && otrackParamArray.length==5){
        		var _F ="-";
        		var formatOtrack = otrackParamArray[0]+_F
        		+otrackParamArray[1]+_F+otrackParamArray[2]+_F
        		+otrackParamArray[3]+"."+otrackParamArray[4];
        		return formatOtrack;
        	}
        	return "";
        };
        oThis.doHasHtag=function(htag){
        	var otrack = pcookie.getOtrackCookie();
        	var otrackParamArray = oThis.getOtrackParam(otrack, htag);
        	if(otrackParamArray){
        		otrack=oThis.formatOtrack(otrackParamArray);
        		pcookie.setOtrackCookie(otrack);
        	}
        	//0级htag 同步到otrack
        	if(htag.indexOf("0.")==0){
        		pcookie.setZeroTrackCookie(htag);
        	}
        };
        oThis.doHasNoHtag=function(){
        	//无htag情况
    		var updateVal = null;
    		var zero_tag =  pcookie.getZeroTrackCookie();
    		if(!zero_tag){//未找到zero_tag
    			var refurl =oThis.referrer;
        		if(refurl){
        			if(refurl.indexOf(howbuy)<=-1){
        				var channel_htag = zeroTagMap.getZeroChannelTagMap();
        				var ks = channel_htag.keyset();
        				for(var zk in ks){
        					if(refurl.indexOf(ks[zk])>=0){
        						updateVal = channel_htag.get(ks[zk]);
        						break;
        					}
        				}
        				//其他渠道
        				if(updateVal == null)
        					updateVal =zeroTagMap.getOtherChannelTag();
        			}
        		}
			}else{
				updateVal = zero_tag;
			}
			
			if(updateVal){
				//更新0级tag
				if(!zero_tag && updateVal!=zeroTagMap.getOtherChannelTag()){
					pcookie.setZeroTrackCookie(updateVal);
				}
				//更新otrack
				var otrackParamArray = oThis.getOtrackParam(null, updateVal);
				otrack=oThis.formatOtrack(otrackParamArray);
        		pcookie.setOtrackCookie(otrack);
			}
        };
        
        oThis.trackHowbuyPageview = bind("_trackHowbuyPageview", 1, function(pageURL) {
            if (_isIgnoreUrl()) {
                try {
                    oThis.initData();
                    oThis.Uc()
                } catch (err) {}
                try {
					oThis.writeVisitCookie();
					oThis.writePageIdCookie();
					oThis.hasOrNotHtagDo();
				} catch (e) {}
                try {
                    oThis.send(pageURL);
                } catch (err) {}
                oThis.A = _false;
               
            }
        });
        oThis.AUPH5=function(){
        	return (window.location.protocol=="https:")?"https://"+ehbc+"/uac/h5uac.do":"http://"+ehbc+"/uac/h5uac.do"
        };
        oThis.trackHowbuyUserActionH5=bind("_trackHowbuyUserActionH5",110,function(paramJson){
        	paramJson=eval(paramJson);
        	try{
        		var params="account="+oThis.getAccount();
        		for(var varJson in paramJson){
        			params+="&"+varJson+"="+paramJson[varJson]
        		}
        		var ajax=new Ajax();
        		ajax.send(oThis.AUPH5(),params);
    		}catch(err){
    			
    		}
        });
        oThis.Uc = function() {
            var win = $HGlobal[_str_window];
            if (Random() % 1000 === 42) {
                try {
                    if (win.external && win.external.onloadT != _undefined || win.webkitPerformance && win.webkitPerformance.timing) {
                        HRegister(12)
                    }
                } catch (ex) {}
            }
        }
        ;
        oThis.deleteCustomVar = bind("_deleteCustomVar", 35, function(index) {
            oThis.initData();
            customVariable.deleteCustomVar(index)
        });
        oThis.getVisitorCustomVar = bind("_getVisitorCustomVar", 50, function(index) {
            oThis.initData();
            return customVariable.getVisitorCustomVar(index)
        });
        oThis.setMaxCustomVariables = bind("_setMaxCustomVariables", 71, function(maxCustomVariables) {
            config.maxCustomVariables = maxCustomVariables
        });
        oThis.link = bind("_link", 101, function(targetUrl, useHash) {
            if (config.allowLinker && targetUrl) {
                oThis.initData();
                $HGlobal[_str_document][_location].href = oThis.getLinkerUrl(targetUrl, useHash)
            }
        });
        oThis.linkByPost = bind("_linkByPost", 102, function(formObject, useHash) {
            if (config.allowLinker && formObject && formObject.action) {
                oThis.initData();
                formObject.action = oThis.getLinkerUrl(formObject.action, useHash)
            }
        });
        oThis.setXKey = bind("_setXKey", 83, function(cache, idx, key) {
            oThis.cache.setKey(cache, idx, key)
        });
        oThis.setXValue = bind("_setXValue", 84, function(cache, idx, value) {
            oThis.cache.setValue(cache, idx, value)
        });
        oThis.getXKey = bind("_getXKey", 76, function(cache, idx) {
            return oThis.cache.getKey(cache, idx)
        });
        oThis.getXValue = bind("_getXValue", 77, function(cache, idx) {
            return oThis.cache.getValue(cache, idx)
        });
        oThis.clearXKey = bind("_clearXKey", 72, function(cache) {
            oThis.cache.clearKey(cache)
        });
        oThis.clearXValue = bind("_clearXValue", 73, function(cache) {
            oThis.cache.clearValue(cache)
        });
        oThis.createXObj = bind("_createXObj", 75, function() {
            oThis.initData();
            return new HCache
        });
        oThis.sendXEvent = bind("_sendXEvent", 78, function(cache) {
            var param = "";
            oThis.initData();
            if (oThis.isSample()) {
                param += "&hutmt=event&hutme=" + Encode(oThis.cache.Serialize(cache)) + oThis.getPageInfo();
                gaAjax.send(param, oThis.account, oThis.domainHash, _false, _true)
            }
        });
        oThis.createEventTracker = bind("_createEventTracker", 74, function(category) {
            oThis.initData();
            return new EventTracker(category,oThis)
        });
        oThis.trackEvent = bind("_trackEvent", 4, function(category, action, label, value) {
            oThis.initData();
            var _eventCache = oThis.eventCache;
            if (_undefined != category && _undefined != action && "" != category && "" != action) {
                _eventCache.clearKey(5);
                _eventCache.clearValue(5);
                (category = _eventCache.setKey(5, 1, category) && _eventCache.setKey(5, 2, action) && (_undefined == label || _eventCache.setKey(5, 3, label)) && (_undefined == value || _eventCache.setValue(5, 1, value))) && oThis.sendXEvent(_eventCache)
            } else {
                category = _false
            }
            return category
        });
        oThis.trackPageLoadTime = bind("_trackPageLoadTime", 100, function() {
            oThis.initData();
            if (!oThis.pageLoadTimeTracker) {
                oThis.pageLoadTimeTracker = new HTimeTracker(oThis,gaAjax)
            }
            return oThis.pageLoadTimeTracker.trackPageLoadTime()
        });
        oThis.getConfig = function() {
            return config
        }
        ;
        oThis.setDomainName = bind("_setDomainName", 6, function(newDomainName) {
            config.domain = newDomainName
        });
        oThis.addOrganic = bind("_addOrganic", 14, function(newOrganicEngine, newOrganicKeyword, prepend) {
            config.organic.splice(prepend ? 0 : config.organic[_length], 0, new HOrganic(newOrganicEngine,newOrganicKeyword))
        });
        oThis.clearOrganic = bind("_clearOrganic", 70, function() {
            config.organic = []
        });
        oThis.addIgnoredOrganic = bind("_addIgnoredOrganic", 15, function(newIgnoredOrganicKeyword) {
            HPush(config.ignoredOrganic, newIgnoredOrganicKeyword)
        });
        oThis.clearIgnoredOrganic = bind("_clearIgnoredOrganic", 97, function() {
            config.ignoredOrganic = []
        });
        oThis.addIgnoredRef = bind("_addIgnoredRef", 31, function(newIgnoredReferrer) {
            HPush(config.ignoredReferral, newIgnoredReferrer)
        });
        oThis.clearIgnoredRef = bind("_clearIgnoredRef", 32, function() {
            config.ignoredReferral = []
        });
        oThis.setAllowHash = bind("_setAllowHash", 8, function(allowHash) {
            config.allowHash = allowHash ? 1 : 0
        });
        oThis.setCampaignTrack = bind("_setCampaignTrack", 36, function(campaignTrack) {
            config.campaignTrack = campaignTrack ? 1 : 0
        });
        oThis.setClientInfo = bind("_setClientInfo", 66, function(clientInfo) {
            config.clientInfo = clientInfo ? 1 : 0
        });
        oThis.getClientInfo = bind("_getClientInfo", 53, function() {
            return config.clientInfo
        });
        oThis.setCookiePath = bind("_setCookiePath", 9, function(cookiePath) {
            config.cookiePath = cookiePath
        });
        oThis.setTransactionDelim = bind("_setTransactionDelim", 82, function(transactionDelim) {
            config.transactionDelim = transactionDelim
        });
        oThis.setCookieTimeout = bind("_setCookieTimeout", 25, function(newDefaultTimeout) {
            oThis.setCampaignCookieTimeout(newDefaultTimeout * 1000)
        });
        oThis.setCampaignCookieTimeout = bind("_setCampaignCookieTimeout", 29, function(cookieTimeoutMillis) {
            config.campaignCookieTimeout = cookieTimeoutMillis
        });
        oThis.setDetectFlash = bind("_setDetectFlash", 61, function(enable) {
            config.detectFlash = enable ? 1 : 0
        });
        oThis.getDetectFlash = bind("_getDetectFlash", 65, function() {
            return config.detectFlash
        });
        oThis.setDetectTitle = bind("_setDetectTitle", 62, function(enable) {
            config.detectTitle = enable ? 1 : 0
        });
        oThis.getDetectTitle = bind("_getDetectTitle", 56, function() {
            return config.detectTitle
        });
        oThis.setLocalGifPath = bind("_setLocalGifPath", 46, function(localGifPath) {
            config.localGifPath = localGifPath
        });
        oThis.getLocalGifPath = bind("_getLocalGifPath", 57, function() {
            return config.localGifPath
        });
        oThis.setLocalServerMode = bind("_setLocalServerMode", 92, function() {
            config.localServerMode = 0
        });
        oThis.setRemoteServerMode = bind("_setRemoteServerMode", 63, function() {
            config.localServerMode = 1
        });
        oThis.setLocalRemoteServerMode = bind("_setLocalRemoteServerMode", 47, function() {
            config.localServerMode = 2
        });
        oThis.getServiceMode = bind("_getServiceMode", 59, function() {
            return config.localServerMode
        });
        oThis.setSampleRate = bind("_setSampleRate", 45, function(newRate) {
            config.sampleRate = newRate
        });
        oThis.setSessionTimeout = bind("_setSessionTimeout", 27, function(newTimeout) {
            oThis.setSessionCookieTimeout(newTimeout * 1000)
        });
        oThis.setSessionCookieTimeout = bind("_setSessionCookieTimeout", 26, function(cookieTimeoutMillis) {
            config.sessionCookieTimeout = cookieTimeoutMillis
        });
        oThis.setAllowLinker = bind("_setAllowLinker", 11, function(allowLinker) {
            config.allowLinker = allowLinker ? 1 : 0
        });
        oThis.setAllowAnchor = bind("_setAllowAnchor", 7, function(allowAnchor) {
            config.allowAnchor = allowAnchor ? 1 : 0
        });
        oThis.setCampNameKey = bind("_setCampNameKey", 41, function(newCampNameKey) {
            config.campaign = newCampNameKey
        });
        oThis.setCampContentKey = bind("_setCampContentKey", 38, function(newCampContentKey) {
            config.content = newCampContentKey
        });
        oThis.setCampIdKey = bind("_setCampIdKey", 39, function(id) {
            config.id = id
        });
        oThis.setCampMediumKey = bind("_setCampMediumKey", 40, function(newCampMedKey) {
            config.medium = newCampMedKey
        });
        oThis.setCampNOKey = bind("_setCampNOKey", 42, function(newCampNOKey) {
            config.nooverride = newCampNOKey
        });
        oThis.setCampSourceKey = bind("_setCampSourceKey", 43, function(newCampSrcKey) {
            config.source = newCampSrcKey
        });
        oThis.setCampTermKey = bind("_setCampTermKey", 44, function(newCampTermKey) {
            config.term = newCampTermKey
        });
        oThis.setCampCIdKey = bind("_setCampCIdKey", 37, function(clid) {
            config.clid = clid
        });
        oThis.getAccount = bind("_getAccount", 64, function() {
            return oThis.account
        });
        oThis.setAccount = bind("_setAccount", 3, function(account) {
            oThis.account = account
        });
        oThis.setNamespace = bind("_setNamespace", 48, function(namespace) {
            config.namespace = namespace ? Encode(namespace) : _undefined
        });
        oThis.getVersion = bind("_getVersion", 60, function() {
            return _version_
        });
        oThis.setAutoTrackOutbound = bind("_setAutoTrackOutbound", 79, emptyFunction);
        oThis.setTrackOutboundSubdomains = bind("_setTrackOutboundSubdomains", 81, emptyFunction);
        oThis.setHrefExamineLimit = bind("_setHrefExamineLimit", 80, emptyFunction);
        oThis.setReferrerOverride = bind("_setReferrerOverride", 49, function(newReferrerOverride) {
            oThis.referrer = newReferrerOverride
        });
        oThis.setCookiePersistence = bind("_setCookiePersistence", 24, function(milliseconds) {
            oThis.setVisitorCookieTimeout(milliseconds)
        });
        oThis.setVisitorCookieTimeout = bind("_setVisitorCookieTimeout", 8, function(cookieTimeoutMillis) {
            config.visitorCookieTimeout = cookieTimeoutMillis
        })
    }
    ;
    var HTrackerFactory = function() {
        var oThis = this
          , bind = HCreateBinder(oThis);
        oThis.anonymizeIp = _false;
        oThis.Trackers = {};
        oThis.Counter = 0;
        oThis._gasoDomain = _undefined;
        oThis._gasoCPath = _undefined;
        oThis.getTracker = bind("_getTracker", 0, function(account, namespace) {
            return oThis.createTracker(account, _undefined, namespace)
        });
        oThis.createTracker = bind("_createTracker", 55, function(account, name, namespace) {
            name && HRegister(23);
            namespace && HRegister(67);
            if (name == _undefined) {
                name = "~" + _HTrackerFactory.Counter++
            }
            return _HTrackerFactory.Trackers[name] = new HTracker(name,account,namespace)
        });
        oThis.getTrackerByName = bind("_getTrackerByName", 51, function(name) {
            name = name || "";
            return _HTrackerFactory.Trackers[name] || _HTrackerFactory.createTracker(_undefined, name)
        });
        oThis.getGaUserPrefs = function() {
            var gaUserPrefs = _o_window[_gaUserPrefs_];
            return gaUserPrefs && gaUserPrefs[_ioo_] && gaUserPrefs[_ioo_]()
        }
        ;
        oThis.anonymizeIp = bind("_anonymizeIp", 16, function() {
            oThis.anonymizeIp = _true
        })
    }
    ;
    var HAsyncTrackerFactory = function() {
        var oThis = this
          , bind = HCreateBinder(oThis);
        oThis.createAsyncTracker = bind("_createAsyncTracker", 33, function(account, name) {
            return _HTrackerFactory.createTracker(account, name || "")
        });
        oThis.getAsyncTracker = bind("_getAsyncTracker", 34, function(name) {
            return _HTrackerFactory.getTrackerByName(name)
        });
        oThis.push = function(commandArray) {
            HRegister(5);
            for (var _arguments = arguments, err = 0, i = 0; i < _arguments[_length]; i++) {
                try {
                    if (typeof _arguments[i] === "function") {
                        _arguments[i]()
                    } else {
                        var name = ""
                          , o = _arguments[i][0]
                          , func = o
                          , idx = o.lastIndexOf(".");
                        if (idx > 0) {
                            name = o[_substring](0, idx);
                            func = o[_substring](idx + 1)
                        }
                        var oTracker = (name == _hat_) ? _HTrackerFactory : (name == _haq_) ? _HAsyncTrackerFactory : _HTrackerFactory.getTrackerByName(name);
                        oTracker[func].apply(oTracker, _arguments[i].slice(1))
                    }
                } catch (ex) {
                    err++
                }
            }
            return err
        }
    }
    ;
    var _HTrackerFactory = new HTrackerFactory;
    var hsingleton = _o_window[_hat_];
    if (hsingleton && typeof hsingleton._getTracker == "function") {
        _HTrackerFactory = hsingleton
    } else {
        _o_window[_hat_] = _HTrackerFactory
    }
    var _HAsyncTrackerFactory = new HAsyncTrackerFactory;
    _tracker_: {
        var gaq = _o_window[_haq_]
          , isArray = _false;
        if (gaq && typeof gaq[_push] == "function") {
            isArray = IsArray(gaq);
            if (!isArray) {
                break _tracker_
            }
        }
        _o_window[_haq_] = _HAsyncTrackerFactory;
        isArray && _HAsyncTrackerFactory[_push].apply(_HAsyncTrackerFactory, gaq)
    }
})();
var _haq = _haq || [];
_haq.push(["_setAccount", "UA-10946093-1"]);
_haq.push(["_trackHowbuyPageview"]);
function trackHowbuyUserActionH5(a){
	_haq.push(["_trackHowbuyUserActionH5",a])
};
/**
 * 获取当前会话的otrack值
 * @returns
 */
function getOtrack(){
	var orackCk = "_hbotrack";
	var ck = document.cookie.match(new RegExp("(^| )"+orackCk+"=([^;]*)(;|$)"));
	if(ck)return ck[2];
	else return "";
}
