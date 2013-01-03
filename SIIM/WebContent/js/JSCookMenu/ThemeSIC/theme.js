
// directory of where all the images are
var cmThemeSICBase = '/SIIF/js/JSCookMenu/ThemeSIC/';


var cmThemeSIC=
{
	prefix:	'ThemeSIC',
  	// main menu display attributes
  	//
  	// Note.  When the menu bar is horizontal,
  	// mainFolderLeft and mainFolderRight are
  	// put in <span></span>.  When the menu
  	// bar is vertical, they would be put in
  	// a separate TD cell.

  	// HTML code to the left of the folder item
  	mainFolderLeft: '&nbsp;',
  	// HTML code to the right of the folder item
  	mainFolderRight: '&nbsp;',
	// HTML code to the left of the regular item
	mainItemLeft: '&nbsp;',
	// HTML code to the right of the regular item
	mainItemRight: '&nbsp;',

	// sub menu display attributes

	// 0, HTML code to the left of the folder item
	folderLeft: '<img alt="" src="' + cmThemeSICBase + 'spacer.gif">',
	// 1, HTML code to the right of the folder item
// adrian	folderRight: '<img alt="" src="' + cmThemeOffice2003Base + 'arrow.gif">',
	folderRight: '<img alt="" src="' + cmThemeSICBase + 'menu-parent.gif">',
	// 2, HTML code to the left of the regular item
	itemLeft: '<img alt="" src="' + cmThemeSICBase + 'spacer.gif">',
	// 3, HTML code to the right of the regular item
	itemRight: '<img alt="" src="' + cmThemeSICBase + 'blank.gif">',
	// 4, cell spacing for main menu
	mainSpacing: 0,
	// 5, cell spacing for sub menus
	subSpacing: 0,

	// move 1st lvl submenu for horizontal menus up a bit to avoid double border
	offsetHMainAdjust:	[0, -1],
	offsetVMainAdjust:	[-1, 0],
	// offset according to Opera, which is correct.
	offsetSubAdjust:	[1, 0]
	// rest use default settings
};

// for horizontal menu split
var cmThemeSICHSplit = [_cmNoClick, '<td class="ThemeSICMenuItemLeft"></td><td colspan="2"><div class="ThemeSICMenuSplit"></div></td>'];
var cmThemeSICMainHSplit = [_cmNoClick, '<td class="ThemeSICMainItemLeft"></td><td colspan="2"><div class="ThemeSICMenuSplit"></div></td>'];
var cmThemeSICMainVSplit = [_cmNoClick, '|'];
