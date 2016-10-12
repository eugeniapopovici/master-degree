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
            {
                fieldLabel: 'Movie',
                name: 'movie'
            }
        ]
    }, {
        items: [
            {fieldLabel: 'Cinema',
            name: 'cinema'}
        ]
    }, {
        items: [
            {fieldLabel: 'Showing',
            name: 'showing'}
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
                            console.log(this);
                            var result = Ext.decode(conn.responseText);
                            console.log(result);
                            Ext.getCmp('mainStore').getStore().loadData(result.data, false);
                            if (result.success) {
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