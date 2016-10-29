var ageRatings = Ext.create('Ext.data.Store', {
    fields: ['age'],
    data: [
        {"age": "12"},
        {"age": "13"},
        {"age": "14"},
        {"age": "15"},
        {"age": "16"},
        {"age": "17"},
        {"age": "18"},
        {"age": "19"},
        {"age": "20"},
        {"age": "21"}
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

var movieRatings = Ext.create('Ext.data.Store', {
    fields: ['rating'],
    data: [
        {"rating": "1"},
        {"rating": "2"},
        {"rating": "3"},
        {"rating": "4"},
        {"rating": "5"},
        {"rating": "6"},
        {"rating": "7"},
        {"rating": "8"},
        {"rating": "9"},
        {"rating": "10"}
    ]
});

var performanceHours = Ext.create('Ext.data.Store', {
    fields: ['id', 'time'],
    data: [
        {"id": "1", "time": "9:00 - 12:00"},
        {"id": "2", "time": "13:00 - 16:00"},
        {"id": "3", "time": "17:00 - 21:00"}
    ]
});

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
                name: 'movieName'
            }, {
                xtype: 'combobox',
                fieldLabel: 'Cinema',
                name: 'cinemaId',
                store: cinemas,
                displayField: 'cinemaName',
                valueField: 'id'
            }, {
                fieldLabel: 'Genre',
                name: 'movieGenre'
            }
        ]
    }, {
        items: [
            {
                xtype: 'combobox',
                fieldLabel: 'Age rating',
                name: 'movieAge',
                store: ageRatings,
                displayField: 'age',
                valueField: 'age'
            }, {
                xtype: 'combobox',
                fieldLabel: 'Movie Rating',
                name: 'movieRating',
                store: movieRatings,
                displayField: 'rating',
                valueField: 'rating'
            }, {
                fieldLabel: 'Performance start time',
                xtype: 'combobox',
                name: 'performanceId',
                store: performanceHours,
                displayField: 'time',
                valueField: 'id'
            }
        ]
    }, {
        items: [
            {
                xtype: 'radiogroup',
                fieldLabel: 'Movie Type',
                // columns: 3,
                layout: {
                    type: 'hbox',
                    align: 'stretch'
                    // align: 'middle',
                    // pack: 'center'
                },
                // layout: 'fit',
                // layout: 'hbox',
                // width: '100%',
                items: [
                    {boxLabel: '2D', name: 'threeD', inputValue: 'false', checked: true, style: 'margin: 0 20px 0 0'},
                    {boxLabel: '3D', name: 'threeD', inputValue: 'true'}
                ]
            }, {
                fieldLabel: 'Showing start date',
                xtype: 'datefield',
                name: 'showingFromDate',
                minValue: new Date()
            }, {
                fieldLabel: 'Showing end date',
                xtype: 'datefield',
                name: 'showingToDate',
                minValue: new Date()
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