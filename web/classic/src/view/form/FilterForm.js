var databases = Ext.create('Ext.data.Store', {
    fields: ['database'],
    data: [
        {"database": "PostgreSQL"},
        {"database": "MSSQL"}
    ]
});

var algorithms = Ext.create('Ext.data.Store', {
    fields: ['algorithm'],
    data: [
        {"algorithm": "Soundex"},
        {"algorithm": "Levenshtein"}
    ]
});

var fields = Ext.create('Ext.data.Store', {
    fields: ['field'],
    data: [
        {"field": "First Name"},
        {"field": "Last Name"},
        {"field": "SSN"},
        {"field": "Country"},
        {"field": "City"},
        {"field": "Company"}
    ]
});


var cinemas = Ext.create('Ext.data.Store', {
    autoLoad: false,
    proxy: {
        type: 'rest',
        url: 'cinema/cinemas/all',
        reader: {
            type: 'json',
            rootProperty: 'data'
        }
    }
});

Ext.define('Cinema.view.form.FilterForm', {
    extend: 'Ext.form.Panel',
    xtype: 'filter-form',

    title: 'Employees Filter',

    layout: 'column',

    defaults: {
        layout: 'form',
        xtype: 'container',
        defaultType: 'textfield',
        style: 'width: 23%'
    },

    items: [{
        items: [
            {
                fieldLabel: 'Text to search',
                name: 'text'
            }
        ]
    }, {
        items: [
            {
                xtype: 'combobox',
                fieldLabel: 'Field',
                name: 'field',
                store: fields,
                displayField: 'field',
                valueField: 'id'
            }
        ]
    }, {
            items: [
                {
                    xtype: 'combobox',
                    fieldLabel: 'Algorithm',
                    name: 'algorithm',
                    store: algorithms,
                    displayField: 'algorithm',
                    valueField: 'id'
                }
            ]
        },{
        items: [
            {
                xtype: 'combobox',
                fieldLabel: 'Database:',
                name: 'database',
                store: databases,
                displayField: 'database',
                valueField: 'id'
            }
        ]
    }],

    buttons: [{
        text: 'Reset',
        handler: function () {
            this.up('filter-form').reset();
            Ext.getCmp('mainStore').getStore().reload();
        }
    }, {
        text: 'Apply',
        handler: function () {
            var formPanel = this.up('filter-form');
            console.log(formPanel);
            if (formPanel.getForm().isValid()) {
                Ext.Ajax.request({
                    url: 'cinema/movies/filter',
                    method: 'POST',
                    headers: {'Content-Type': 'application/json'},
                    params: Ext.JSON.encode(formPanel.getValues(false, true)),
                    // params: Ext.Object.toQueryString(formPanel.getValues()),
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
    }]
});