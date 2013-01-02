// Calendario.js

var DotJ_today = new Date();
var DotJ_daysInMonth = new Array(31,29,31,30,31,30,31,31,30,31,30,31);

//*********  CALENDAR FUNCTIONS ***********

DotJ_Calendar.render = function() {
	var firstDay = new Date(this.year,this.month-1,1);
	this.computeDaySlots(firstDay);
	this.renderMonth();
	this.renderDays(firstDay);
}

DotJ_Calendar.computeDaySlots = function(firstDay) {
	var theFirstDay = DotJ_computeDay(firstDay.getDay(), this.firstDayOfWeek);
	var lastDay = this.computeLastDay() + theFirstDay;
	for (counter = 1; counter <= 42; counter++) {
		if (counter > theFirstDay && counter <= lastDay) {
			this.daySlots[counter] = counter - theFirstDay;
		} else {
			this.daySlots[counter] = '';
		}
	}
}

function DotJ_computeDay(day, firstDayOfWeek) {
	var result = day;
	result = result - (firstDayOfWeek-1);
	return result;
}

DotJ_Calendar.computeLastDay = function() {
	var lastDay = DotJ_daysInMonth[this.month-1];
	if (this.month == 2) {
		lastDay = DotJ_daysInFebruary(this.year);
	}
	return lastDay;
}

DotJ_Calendar.searchForEvent = function(day) {
	var i = 0;
	if (this.events) {
		for (i = 0; i < this.events.length; i++) {
			if (this.year == this.events[i].date.getFullYear() &&
				this.month == (this.events[i].date.getMonth()+1) &&
				day == this.events[i].date.getDate()) {
				return i;
			}
		}
	}
	return -1;
}

function DotJ_findSunday(year, month) {
	var i = 0;
	for (i = 1; i <= 7; i++) {
		var dt = new Date(year, month, i);
		if (dt.getDay() == 0) return i;
	}
    return 1;
}

DotJ_Calendar.renderDays = function(firstDay) {
	var clsName, theDay, eventId = -1, html;
	var tempToday = new Date(DotJ_today.getFullYear(), DotJ_today.getMonth(), DotJ_today.getDate());

    var hidePrevYYIcon = false;
	if (this.startDate != null) {
		var tempStartDate = new Date(this.startDate.getFullYear(), this.startDate.getMonth(), 1);
		var prevYearCutoff = new Date(this.year, this.month-1, 1);
		prevYearCutoff.setYear(prevYearCutoff.getFullYear()-1);
		var dayDiff = Math.floor((tempStartDate.getTime() - prevYearCutoff.getTime()) / 1000 / 60 / 60 / 24);
        hidePrevYYIcon = (dayDiff > 0) ? true : false;
	}

    var hideNextYYIcon = false;
	if (this.endDate != null) {
		var nextYearCutoff = new Date(this.year, this.month-1, 1);
		nextYearCutoff.setYear(nextYearCutoff.getFullYear()+1);
		dayDiff = Math.floor((nextYearCutoff.getTime() - this.endDate.getTime()) / 1000 / 60 / 60 / 24);
        hideNextYYIcon = (dayDiff > 0) ? true : false;
    }

    var hidePrevMMIcon = false;
    var hideNextMMIcon = false;

	var theFirstDay = DotJ_computeDay(firstDay.getDay(), this.firstDayOfWeek);
	var Sunday = DotJ_findSunday(this.year, this.month-1);
	var SundayColumn = Sunday + theFirstDay;
	if (SundayColumn >= 7) SundayColumn = SundayColumn - 7;
	var SaturdayColumn = SundayColumn - 1;
	if (SaturdayColumn == -1) SaturdayColumn = 6;

	for (i = 1; i <= 42; i++) {
		var emptyCell = (this.daySlots[i] == '') ? true : false;

		clsName = this.style('Weekday');
		if (this.hiliteToday && this.year == DotJ_today.getFullYear() &&
			(this.month-1) == DotJ_today.getMonth() &&
			(i - theFirstDay) == DotJ_today.getDate()) {
			clsName = this.style('CurrentDay');
		} else if (this.year == this.selectedYear &&
				   (this.month) == this.selectedMonth &&
				   (i - theFirstDay) == this.selectedDay) {
			clsName = this.style('SelectedDay');
		} else {
			if ((i % 7 == SundayColumn) || (i % 7 == SaturdayColumn))
				clsName = this.style('Weekend');
		}

		html = "";
		eventId = this.searchForEvent((i - theFirstDay));
		if (eventId != -1 && this.mode == 'fixed') {
			clsName = this.style('Event');
			html = html + "<a class='" + clsName + "'";
			if (this.events[eventId].target != null)
				html = html + " target='" + this.events[eventId].target + "'";

			if (this.events[eventId].url != null) {
				var oldValue = DotJ_param_date;
				var tempDate = this.events[eventId].date;
				var newURL = DotJ_makeNewURL(this.events[eventId].url, "dotjdate",
											 DotJ_makeDate(tempDate.getFullYear(), tempDate.getMonth()+1, tempDate.getDate(), this.format), oldValue);
				if (this.events[eventId].description != null) {
					oldValue = DotJ_param_description;
					newURL = DotJ_makeNewURL(newURL, "dotjdesc", escape(this.events[eventId].description), oldValue);
				}
				if (this.events[eventId].key != null) {
					oldValue = DotJ_param_key;
					newURL = DotJ_makeNewURL(newURL, "dotjkey", escape(this.events[eventId].key), oldValue);
					if (this.events[eventId].keyname != null) {
					    oldValue = this.events[eventId].key;
					    newURL = DotJ_makeNewURL(newURL, this.events[eventId].keyname, escape(this.events[eventId].key), oldValue);
					}
				}
				html = html + " href='" + newURL + "'";
			}

			if (this.events[eventId].description != null) {
				html = html + " title=" + DotJ_safeJSString(this.events[eventId].description);
			}
			html = html + ">";
		}

        if (!emptyCell && this.startDate != null) {
            if (this.year < this.startDate.getFullYear()) {
                clsName = clsName + ' ' + this.style('NonselectableDay');
                hidePrevMMIcon = true;
            } else if (this.year == this.startDate.getFullYear() && (this.month-1) < this.startDate.getMonth()) {
                clsName = clsName + ' ' + this.style('NonselectableDay');
                hidePrevMMIcon = true;
            } else if (this.year == this.startDate.getFullYear() && (this.month-1) == this.startDate.getMonth() && (i - theFirstDay) < this.startDate.getDate()) {
                clsName = clsName + ' ' + this.style('NonselectableDay');
                hidePrevMMIcon = true;
            }
        }

        if (!emptyCell && this.endDate != null) {
            if (this.year > this.endDate.getFullYear()) {
                clsName = clsName + ' ' + this.style('NonselectableDay');
                hideNextMMIcon = true;
            } else if (this.year == this.endDate.getFullYear() && (this.month-1) > this.endDate.getMonth()) {
                clsName = clsName + ' ' + this.style('NonselectableDay');
                hideNextMMIcon = true;
            } else if (this.year == this.endDate.getFullYear() && (this.month-1) == this.endDate.getMonth() && (i - theFirstDay) > this.endDate.getDate()) {
                clsName = clsName + ' ' + this.style('NonselectableDay');
                hideNextMMIcon = true;
            }
        }

		if (emptyCell) html = html + "&nbsp;";
		else html = html + this.daySlots[i];

		if (eventId != -1 && this.mode == 'fixed')
			html += "</a>";

		var cell = this.getCalTDId(i);
		var objCell = DotJ_getObj(cell);
		DotJ_setText(cell, html);
		objCell.className = clsName;
		if (this.mode == 'fixed') {
			DotJ_setCursorToDefault(objCell);
		} else {
			if (emptyCell) {
				DotJ_setCursorToDefault(objCell);
			} else if (clsName.indexOf('NonselectableDay') == -1) {
				DotJ_setCursorToPointer(objCell);
			} else {
				DotJ_setCursorToDefault(objCell);
			}
		}
	}

	if (this.hdrPrevMM) {
		this.hdrPrevMM.innerHTML = this.hdrPrevMMInnerHTML;
		if (hidePrevMMIcon) this.hdrPrevMM.innerHTML = this.hdrPrevMMBlankInnerHTML;
	}

	if (this.hdrNextMM) {
		this.hdrNextMM.innerHTML = this.hdrNextMMInnerHTML;
		if (hideNextMMIcon) this.hdrNextMM.innerHTML = this.hdrNextMMBlankInnerHTML;
	}

	if (this.hdrPrevYY) {
		this.hdrPrevYY.innerHTML = this.hdrPrevYYInnerHTML;
		if (hidePrevYYIcon) this.hdrPrevYY.innerHTML = this.hdrPrevYYBlankInnerHTML;
	}

	if (this.hdrNextYY) {
		this.hdrNextYY.innerHTML = this.hdrNextYYInnerHTML;
		if (hideNextYYIcon) this.hdrNextYY.innerHTML = this.hdrNextYYBlankInnerHTML;
	}
}

function DotJ_doPrev(cal,unit) {
	if (unit == 'm') cal.decMonth();
	else cal.decYear();
cal.toggle();
	cal.render();	
cal.toggle();	
}

function DotJ_gotoToday(cal) {
	cal.selectedYear = DotJ_today.getFullYear();
	cal.year = DotJ_today.getFullYear();
	cal.selectedMonth = DotJ_today.getMonth() + 1;
	cal.month = DotJ_today.getMonth() + 1;
	cal.selectedDay = DotJ_today.getDate();
	cal.day = DotJ_today.getDate();

cal.toggle();
	cal.render();
cal.toggle();
}

function DotJ_doNext(cal,unit) {
	if (unit == 'm') cal.incMonth();
	else cal.incYear();
cal.toggle();
	cal.render();
cal.toggle();	
}

DotJ_Calendar.decMonth = function() {
	this.month -= 1;
	if (this.month <= 0) {
		this.month = 12;
		this.decYear();
	}
}

DotJ_Calendar.incMonth = function() {
	this.month += 1;
	if (this.month >= 13) {
		this.month = 1;
		this.incYear();
	}
}

DotJ_Calendar.decYear = function() {
	this.year -= 1;
}

DotJ_Calendar.incYear = function() {
	this.year += 1;
}

DotJ_Calendar.renderMonth = function() {
	DotJ_setText(this.getCalHdrId(), '&nbsp;' + this.months[this.month] + ' ' + this.year + '&nbsp;');
}

DotJ_Calendar.hide = function() {
	var el = this.div;
	DotJ_hideElement(el);
	this.visible = false;
	//if (DotJ_is_ie4up) DotJ_hiddenSelects.checkOverlappingSelects(el, true);
}

DotJ_Calendar.toggle = function() {
	var el = this.div;

	if (DotJ_getVisibility(el) == "show" || DotJ_getVisibility(el) == "visible") {
		this.hide();
	} else {
		DotJ_displayElement(el);
		this.visible = true;
		//if (DotJ_is_ie4up) DotJ_hiddenSelects.checkOverlappingSelects(el, false);
	}
}

DotJ_Calendar.show = function(field) {
	this.field = field;

	var objField = DotJ_findFormField(field);
	if (objField == null && this.label == null) {
		var msg = "dotJ Javascript Error: Cannot locate form field '" + field + "'. \n\nThe item must be under a <form> tag.";
		alert(msg);
		return;
	}
	var inputDate;
	inputDate = DotJ_isDate(objField.value, this.format);
	if (inputDate != null) {
		this.selectedYear = inputDate.getFullYear();
		this.year = inputDate.getFullYear();
		this.selectedMonth = inputDate.getMonth() + 1;
		this.month = inputDate.getMonth() + 1;
		this.selectedDay = inputDate.getDate();
		this.day = inputDate.getDate();
	}
	var calWidth = DotJ_getWidth(this.div);
	var calHeight = DotJ_getHeight(this.div);

	var x=0, y=0;
	
	this.render();
	this.toggle();
}

DotJ_Calendar.selectDay = function(cellId) {
	var cell = DotJ_getObj(cellId);
	var day = cell.innerHTML;
	if (DotJ_isPositiveInteger(day) == false || cell.className.indexOf('NonselectableDay') != -1) return;
	if (this.label == null) {

		var objField = DotJ_findFormField(this.field);

		objField.value = DotJ_makeDate(this.year, this.month, day, this.format);
		if (typeof(objField.onchange) == 'function') {
			objField.onchange();
		}
	} else {
		var selDate = DotJ_makeDate(this.year, this.month, day, this.format);
		this.label.innerHTML = selDate;
		if (this.labelId != '') {
			var objLbl = DotJ_findFormField(this.labelId);
			if (objLbl != null) objLbl.value = selDate;
		}
	}
	this.selectedYear = this.year;
	this.selectedMonth = this.month;
	this.selectedDay = day;

	this.toggle();
}

DotJ_Calendar.getCalId = function() {
	return this.id;
}

DotJ_Calendar.getCalHdrId = function() {
	return this.getCalId() + "_hdr";
}

DotJ_Calendar.getCalTDId = function(day) {
	return this.getCalId() + "_TD_" + day;
}

DotJ_Calendar.style = function(styleName) {
	return this.stylePrefix + styleName;
}


DotJ_Calendar.computePrevHdrWidth = function() {
	this.hdrPrevYY = DotJ_getObj(this.id + '_HdrPrevYY');
	if (this.hdrPrevYY != null) {
		this.hdrPrevYYWidth = DotJ_getWidth(this.hdrPrevYY);
		this.hdrPrevYYInnerHTML = this.hdrPrevYY.innerHTML;
	}
	this.hdrPrevMM = DotJ_getObj(this.id + '_HdrPrevMM');
	if (this.hdrPrevMM != null) {
		this.hdrPrevMMWidth = DotJ_getWidth(this.hdrPrevMM);
		this.hdrPrevMMInnerHTML = this.hdrPrevMM.innerHTML;
	}
}

DotJ_Calendar.computeNextHdrWidth = function() {
	this.hdrNextYY = DotJ_getObj(this.id + '_HdrNextYY');
	if (this.hdrNextYY != null) {
		this.hdrNextYYWidth = DotJ_getWidth(this.hdrNextYY);
		this.hdrNextYYInnerHTML = this.hdrNextYY.innerHTML;
	}
	this.hdrNextMM = DotJ_getObj(this.id + '_HdrNextMM');
	if (this.hdrNextMM != null) {
		this.hdrNextMMWidth = DotJ_getWidth(this.hdrNextMM);
		this.hdrNextMMInnerHTML = this.hdrNextMM.innerHTML;
	}
}

function DotJ_Calendar(id, format, mode, year, month, selectedDay, hiliteToday, stylePrefix, label, labelId, startDate, endDate, firstDayOfWeek) {
	this.id = id;
	this.div = DotJ_getObj(id);
	this.format = format;
	this.mode = mode;
	this.year = year;
	this.month = month;
	this.selectedYear = year;
	this.selectedMonth = month;
	this.selectedDay = selectedDay;
	this.hiliteToday = hiliteToday;
	this.events = null;
	this.months = null;
	this.daySlots = null;
	this.visible = false;
	this.stylePrefix = stylePrefix;
	this.label = label;
	this.labelId = labelId;
	this.startDate = null;
	if (startDate != null) this.startDate = DotJ_isDate(startDate, this.format);
	if (endDate != null) this.endDate = DotJ_isDate(endDate, this.format);
	this.firstDayOfWeek = firstDayOfWeek;

	this.style = DotJ_Calendar.style;
	this.render = DotJ_Calendar.render;
	this.renderMonth = DotJ_Calendar.renderMonth;
	this.renderDays = DotJ_Calendar.renderDays;
	this.computeDaySlots = DotJ_Calendar.computeDaySlots;
	this.computeLastDay = DotJ_Calendar.computeLastDay;
	this.searchForEvent = DotJ_Calendar.searchForEvent;
	this.decMonth = DotJ_Calendar.decMonth;
	this.incMonth = DotJ_Calendar.incMonth;
	this.decYear = DotJ_Calendar.decYear;
	this.incYear = DotJ_Calendar.incYear;
	this.hide = DotJ_Calendar.hide;
	this.show = DotJ_Calendar.show;
	this.toggle = DotJ_Calendar.toggle;
	this.selectDay = DotJ_Calendar.selectDay;
	this.getCalId = DotJ_Calendar.getCalId;
	this.getCalHdrId = DotJ_Calendar.getCalHdrId;
	this.getCalTDId = DotJ_Calendar.getCalTDId;
	this.computePrevHdrWidth = DotJ_Calendar.computePrevHdrWidth;
	this.computeNextHdrWidth = DotJ_Calendar.computeNextHdrWidth;
}

function DotJ_makeSlots() {
	var arr = new Array(43);
	var i;
	for (i = 0; i < 43; i++)
		arr[i] = '';
	return arr;
}

//Modifica dinamicamente el html de la pagina
function DotJ_setText(id, text) {
	DotJ_writeLayer(DotJ_getObj(id), text);
}
function DotJ_writeLayer(lay, html) {
	if (DotJ_is_nav4) {
		var l = lay.document;
		l.write(html);
		l.close();
	} else if (DotJ_is_ie4up) {
		lay.innerHTML = html;
	} else if (DotJ_MozillaFamily()) {
		var r = document.createRange();
		r.setStartBefore(lay);
		var f = r.createContextualFragment(html);
		while (lay.hasChildNodes()) {
			lay.removeChild(lay.lastChild);
		}
		lay.appendChild(f);
	}
}
//

function DotJ_findFormField(fieldName) {
	var objField = null, form, iForm = 0, iElement = 0;
	var forms = document.forms;
	if (typeof(fieldName) == 'undefined' || fieldName == null || fieldName == '') return null;

	for (iForm = 0; iForm < forms.length; iForm++) {
		form = forms[iForm];

		for (iElement = 0; iElement < form.elements.length; iElement++) {
			var tempId = form.elements[iElement].name;
			if (typeof(tempId) == 'undefined' || tempId == '') tempId = form.elements[iElement].id;
			if (typeof(tempId) == 'undefined' || tempId == '') {
				;
			} else {
				if (tempId.toLowerCase() == fieldName.toLowerCase()) {
					objField = form.elements[iElement];
					break;
				}
			}
		}
	}

	if (objField == null) objField = DotJ_getObj(fieldName);

	return objField;
}

function DotJ_isDate (theDate, inFormat) {
	var format = inFormat.toLowerCase();
	var result = null, year, month, day, delim;
	var isYear = null;
	if (format == "date1" || format == "date3" || format == "date8" || format == "date11")
		delim = '/'
	else if (format == "date2" || format == "date4" || format == "date9" || format == "date12")
		delim = '-'
	if (format == "date1" || format == "date2" || format == "date8" || format == "date9") {
		var pos1 = theDate.indexOf(delim)
		if (pos1 == -1) return null;
		month = theDate.substring(0, pos1)
		var pos2 = theDate.lastIndexOf(delim)
		if (pos2 == pos1) return null;
		day = theDate.substring(pos1+1, pos2)
		year = theDate.substring(pos2+1, theDate.length)
		if (format == "date8" || format == "date9") {
			isYear = DotJ_isShortYear(year);
		}
	} else if (format == "date3" || format == "date4") {
		var pos1 = theDate.indexOf(delim)
		if (pos1 == -1) return null;
		year = theDate.substring(0, pos1)
		var pos2 = theDate.lastIndexOf(delim)
		if (pos2 == pos1) return null;
		month = theDate.substring(pos1+1, pos2)
		day = theDate.substring(pos2+1, theDate.length)
	} else if (format == "date11" || format == "date12") {
		var pos1 = theDate.indexOf(delim)
		if (pos1 == -1) return null;
		day = theDate.substring(0, pos1)
		var pos2 = theDate.lastIndexOf(delim)
		if (pos2 == pos1) return null;
		month = theDate.substring(pos1+1, pos2)
		year = theDate.substring(pos2+1, theDate.length)
	} else if (format == "date5") {
		if (theDate.length != 8) return null;
		month = theDate.substring(0, 2)
		day = theDate.substring(2, 4)
		year = theDate.substring(4, 8)
	} else if (format == "date6") {
		if (theDate.length != 8) return null;
		year = theDate.substring(0, 4)
		month = theDate.substring(4, 6)
		day = theDate.substring(6, 8)
	} else if (format == "date7") {
		if (theDate.length != 6) return null;
		month = theDate.substring(0, 2)
		day = theDate.substring(2, 4)
		year = theDate.substring(4, 6)
		isYear = DotJ_isShortYear(year);
	} else if (format == "date10") {
		if (theDate.length != 8) return null;
		day = theDate.substring(0, 2)
		month = theDate.substring(2, 4)
		year = theDate.substring(4, 8)
	}

	if (isYear == null) isYear = DotJ_isYear(year);
	if (!(isYear && DotJ_isMonth(month) && DotJ_isDay(day))) return null;
	var intYear = parseInt(year,10);
	//modifico el 45 por 15
	if (intYear < 15) {
		intYear = 2000 + intYear;
	} else if (intYear < 100) {
		intYear = 1900 + intYear;
	}
	var intMonth = parseInt(month,10);
	var intDay = parseInt(day,10);
	if (intDay > DotJ_daysInMonth[intMonth-1]) return null;
	if ((intMonth == 2) && (intDay > DotJ_daysInFebruary(intYear))) return null;
	return new Date(intYear, intMonth-1, intDay);
}

function DotJ_getWidth(el) {
	return el.offsetWidth;
}

function DotJ_getHeight(el) {
	return el.offsetHeight;
}

function DotJ_setCursorToDefault(obj) {
	obj.style.cursor = 'default';
}

function DotJ_makeDate (YYYY, mm, dd, fmt) {
	var MM = "" + mm;
	var DD = "" + dd;
	if (MM.length == 1) MM = "0" + MM;
	if (DD.length == 1) DD = "0" + DD;

	if (fmt == "date1") {
		return MM + "/" + DD + "/" + YYYY;
	} else if (fmt == "date2") {
		return MM + "-" + DD + "-" + YYYY;
	} else if (fmt == "date3") {
		return YYYY + "/" + MM + "/" + DD;
	} else if (fmt == "date4") {
		return YYYY + "-" + MM + "-" + DD;
	} else if (fmt == "date5") {
		return MM + "" + DD + "" + YYYY;
	} else if (fmt == "date6") {
		return YYYY + "" + MM + "" + DD;
	} else if (fmt == "date7") {
		var temp = "" + YYYY;
		return MM + "" + DD + "" + temp.substring(2);
	} else if (fmt == "date8") {
		var temp = "" + YYYY;
		return MM + "/" + DD + "/" + temp.substring(2);
	} else if (fmt == "date9") {
		var temp = "" + YYYY;
		return MM + "-" + DD + "-" + temp.substring(2);
	} else if (fmt == "date10") {
		return DD + "" + MM + "" + YYYY;
	} else if (fmt == "date11") {
		return DD + "/" + MM + "/" + YYYY;
	} else if (fmt == "date12") {
		return DD + "-" + MM + "-" + YYYY;
	} else {
		return "";
	}
}


function DotJ_daysInFebruary (year) {
	return (((year % 4 == 0) && ((!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}

//muestra la manito , solo modo "popup"
function DotJ_setCursorToPointer(obj) {
	if (DotJ_is_ie4up) obj.style.cursor = 'hand';
	else obj.style.cursor = 'pointer';
}
function DotJ_isShortYear (s) {
	var re = /^\d{2}$/
	var isMatch = re.exec(s);
	return (isMatch != null && s == isMatch[0]);
}
function DotJ_isMonth (s) {
	return DotJ_isIntegerInRange (s, 1, 12);
}

function DotJ_isDay (s) {
	return DotJ_isIntegerInRange (s, 1, 31);
}
function DotJ_isYear (s) {
	var re = /^\d{4}$/
	var isMatch = re.exec(s);
	return (isMatch != null && s == isMatch[0]);
}
