
CKEDITOR.editorConfig = function( config )
{
    // Define changes to default configuration here. For example:
//    config.toolbar = 'Basic';
//  config.toolbar = [['Full','-', 'Autosave']];
    config.toolbar = [['Source','Save','Preview']];

    config.extraPlugins = 'autosave';

    config.forcePasteAsPlainText = true;
    






};