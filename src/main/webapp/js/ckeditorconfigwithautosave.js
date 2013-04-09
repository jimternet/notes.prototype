
CKEDITOR.editorConfig = function( config )
{
    // Define changes to default configuration here. For example:
//    config.toolbar = 'Basic';
//  config.toolbar = [['Full','-', 'Autosave']];
    config.toolbar = [['Source','Save','Preview','-', 'Autosave']];

    config.extraPlugins = 'autosave';
//    config.autosaveTargetUrl = 'http://localhost:8080/notes.prototype/us/hennepin/services/connector.java';
    config.autosaveTargetUrl = 'http://localhost:8080/notes.prototype/NotePersister';
    config.forcePasteAsPlainText = true;
    
	config.autosaveSensitivity = 45;
	config.autosaveRefreshTime = 90;
	config.autosaveRequestTimeout = 10;	
	config.autosaveMethod = 'POST';
	config.autosaveContentParamName = 'content';	
	config.autosaveUseOnBeforeUnload = true;	
//	config.autosaveRequestParams = 'someName=false&someName2=someValue&someName3=5';
    config.autoParagraph = false;





};