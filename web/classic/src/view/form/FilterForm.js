Ext.define('Cinema.view.form.FilterForm', {
    extend: 'Ext.form.Panel',
    xtype: 'filter-form',

    title: 'Movie Filter',

    layout: 'column',

    defaults: {
        layout: 'form',
        xtype: 'container',
        defaultType: 'textfield',
        style: 'width: 33%'
    },

    items: [{
        items: [
            {fieldLabel: 'Film'}
        ]
    }, {
        items: [
            {fieldLabel: 'Cinema'}
        ]
    }, {
        items: [
            {fieldLabel: 'Showing'}
        ]
    }],

    buttons: [
        {
            text: 'Apply',
            handler: function () {
                var formPanel = this.up('filter-form');
                if (formPanel.getForm().isValid()) {
                    Ext.Ajax.request({
                        url: 'cinema/movies/filter',
                        params: Ext.Object.toQueryString(formPanel.getValues()),
                        success: function (conn, response, options, eOpts) {
                            var result = Ext.decode(conn.responseText);
                            if (result.success) {
                                Ext.Msg.alert('Success!', 'Stock was saved.');
                            } else {
                                // TODO
                            }
                        },
                        failure: function (conn, response, options, eOpts) {
                            // TODO
                        }
                    });
                }
            }
        }
    ]
});