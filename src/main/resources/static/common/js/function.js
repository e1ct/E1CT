jQuery.fn.serializeObject = function() { 
	var obj = null; 
	try { 
		if( this[0].tagName && this[0].tagName.toUpperCase() == "FORM" ) {
			var arr = this.serializeArray(); 
			if( arr ){ 
				obj = {}; 
				jQuery.each( arr, function(){ 
					obj[this.name] = this.value; 
				}); 
			} 
		} 
	} catch(e) { 
		alert(e.message); 
	} finally {} 
	return obj; 
}

Date.prototype.format = function(f) {
	if( !this.valueOf() ) return "";
	
	var weekName = [ "SUM", "MON", "TUE", "WED", "THU", "FRI", "SAT" ];
	var d = this;
	
	return f.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p)/gi, function( $1 ) {
		switch( $1 ) {
		case "yyyy": return d.getFullYear();
		case "yy": return (d.getFullYear() % 1000).zf(2);
		case "MM": return (d.getMonth() + 1).zf(2);
		case "dd": return d.getDate().zf(2);
		case "E": return weekName[d.getDay()];
		case "HH": return d.getHours().zf(2);
		case "hh": return ( ( h = d.getHours() % 12 ) ? h : 12 ).zf(2);
		case "mm": return d.getMinutes().zf(2);
		case "ss": return d.getSeconds().zf(2);
		case "a/p": return d.getHours() < 12 ? "AM" : "PM";
		default: return $1;
		}
	});
};

String.prototype.string = function( len ) {
	var s = '', i = 0;
	while( i++ < len ) {
		s += this;
	}
	return s;
}

String.prototype.zf = function( len ) {
	return "0".string(len - this.length) + this;
}

String.prototype.replaceAll = function( regex, replace ) {
	return this.split( regex ).join( replace );
}

String.prototype.isEmpty = function() {
	if( this == undefined ) return true;
	if( this.trim().length > 0 ) {
		return false;
	}
	
}

Number.prototype.zf = function( len ) {
	return this.toString().zf(len);
}

function isEmail(asValue) {
	var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	return regExp.test(asValue); // 형식에 맞는 경우 true 리턴	
}

function pageMove( formName, page ) {
	document[formName].action += "?page=" + (page-1);
	document[formName].submit();
}

function paging( selector, totalCnt, pageSize, pageNumber, formName ) {
	
	if( selector == "" ) selector = "#paging";
	var action = document[formName].action;
	var totalPage = Math.ceil( totalCnt / pageSize );
	var pageStart = Math.floor( pageNumber / 10 ) * 10 + 1;
	var pageEnd = pageStart + 9 < totalPage ? pageStart + 9 : totalPage;
	var currentPage = pageNumber;
	
	if( currentPage >= 10 ) {
		var prevPageAnchor = document.createElement( "a" );
		$( prevPageAnchor ).html("&lt;&lt;");
		$( prevPageAnchor ).attr( "href", "javascript:pageMove('" + formName + "', " + (pageStart - 1) + ")" );
		$( selector ).append( prevPageAnchor );
	}
	
	for( var i=pageStart; i<=pageEnd; i++ ) {
		var pageAnchor = document.createElement( "a" );
		$( pageAnchor ).html(i);
		$( pageAnchor ).attr( "href", "javascript:pageMove('" + formName + "', " + i + ")" );
		$( selector ).append( pageAnchor );
	}
	
	if( pageEnd < totalPage ) {
		var nextPageAnchor = document.createElement( "a" );
		$( nextPageAnchor ).html("&gt;&gt;");
		$( nextPageAnchor ).attr( "href", "javascript:pageMove('" + formName + "', " + (pageEnd + 1) + ")" );
		$( selector ).append( nextPageAnchor );
	}
}

function cntrDetailModal( cntrNo ) {
	/*
	var fillValue = function( field, value ) {
		var prefix = "#cntrDetailModal [data-field='";
		var suffix = "']";
		
		$( prefix + field + suffix ).html( value );
	}
	
	var callback = function( d ) {
		var type = "General";
		if( d.tyCd >= 30 && d.tyCd < 50 ) {
			type = "Reefer";
		} else if( d.tyCd >= 50 && d.tyCd < 60 ) {
			type = "Open Top";
		} else if( d.tyCd >= 60 && d.tyCd < 70 ) {
			type = "Platform";
		} else if( d.tyCd >= 70 && d.tyCd < 80 ) {
			type = "Tank";
		} else if( d.tyCd >= 80 && d.tyCd < 90 ) {
			type = "Special";
		} else if( d.tyCd >= 90 && d.tyCd < 100 ) {
			type = "Air";
		}
		
		fillValue( 'cntrNo', '컨테이너 번호 : <b>' + d.cntrNo + "</b>" );
		fillValue( 'ship', d.shipCd + "-" + d.callNo + "-" + d.callYy );
		fillValue( "shipNm", d.shipNm );
		fillValue( "operCd", d.operCd );
		fillValue( "operKnm", d.operKnm );
		fillValue( "curStat", d.curStat + "-" + d.fmCd );
		fillValue( "szCd", d.szCd + "/" + d.tyCd + " (" + type + ")" );
		fillValue( "rtnCd", d.rtnCd == "C" ? "Cancel" : ( d.rtnCd == "R" ? "Return" : "" ) );
		fillValue( "tsId", d.tsId );
		if( d.truckerCdOut ) {
			fillValue( "sealNo", d.truckerCd + "-" + d.carNo + "-" + "(" + d.sealNo + ")" );
		} else {
			fillValue( "sealNo", d.truckerCdOut + "-" + d.carNoOut + "-" + "(" + d.sealNo + ")" );
		}
		fillValue( "weight", d.weight );
		fillValue( "imdg", "IMDG : " + d.imdg + " UNNO : " + d.unno );
		fillValue( "temp", d.tempId == "CEL" ? ( d.temp + "˚C" ) : ( d.tempId == "FAH" ? ( d.temp + "˚F" ) : "" ) );
		if( d.sTbay ) {
			fillValue( "sTloca", d.sTbay + "-" + d.sTrow + "-" + d.sTtier );
		}
		if( d.yTblock ) {
			fillValue( "yTloca", d.yTblock + "-" + d.yTrow + "-" + d.yTtier );
		}
		fillValue( "pod", d.pod + "/" + d.pol );
		fillValue( "inspId", d.inspId );
		fillValue( "ginDt", d.ginDt ? d.ginDt : d.gcDdt );
		fillValue( "goutDt", d.gcLdt ? d.gcLdt : d.goutDt );
		fillValue( "bondId", d.bondId == "B" ? "보세" : ( d.bondId == "C" ? "부두통관" : "" ) );
		fillValue( "yardDays", d.yardDay > 0 ? d.yardDay + "일" : "0" );
		fillValue( "bkNo", d.orderNo );
		
		$("#cntrDetailModal").iziModal('open');
	}
	
	send( '/info/terminal/cntrDetailInfo', { "cntrNo": cntrNo }, callback )
	*/
}

function send( url, data, callback, reqType ) {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");

	$.ajax({
		type: 'POST',
		url: url,
		contentType: "application/json",
		dataType: "json",
		data: JSON.stringify( data ),
		beforeSend: function(xhr) {
			xhr.setRequestHeader( header, token );
		},
		async: true,
		cache: false,
		success: function( d ) {
			callback( d )
		},
		error: function( xhr, status, error ) {
			console.log( xhr );
			console.log( status );
			console.log( error );
			alert( '예기치 못한 에러가 발생하였습니다. 관리자에게 문의해주세요.' );
		}
	});
}

function getParameter( param ) {
	var returnValue;
	var url = location.href;
	var parameters = (url.slice( url.indexOf('?') + 1, url.length ) ).split('&');
	for( var i=0; i<parameters.length; i++ ) {
		var varName = parameters[i].split('=')[0];
		if( varName.toUpperCase() == param.toUpperCase() ) {
			returnValue = parameters[i].split('=')[1];
			return decodeURIComponent( returnValue );
		}
	}
}

function cntrPopup( cntrNo ) {
	window.open( "/info/popup/cntrDetail?cntrNo=" + cntrNo, '', 'width=600, height=500' );
}

function cntrPopupDetail( cntrNo, ioCd ) {
	window.open( "/info/popup/cntrDetailTwo?cntrNo=" + cntrNo + "&ioCd=" + ioCd, '', 'width=600, height=400' );
}
