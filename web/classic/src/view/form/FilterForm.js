var ageRatings = Ext.create('Ext.data.Store', {
    fields: ['age'],
    data : [
        {"age":"12"},
        {"age":"13"},
        {"age":"14"},
        {"age":"15"},
        {"age":"16"},
        {"age":"17"},
        {"age":"18"},
        {"age":"19"},
        {"age":"20"},
        {"age":"21"}
    ]
});

var movieRatings = Ext.create('Ext.data.Store', {
    fields: ['rating'],
    data : [
        {"rating":"1"},
        {"rating":"2"},
        {"rating":"3"},
        {"rating":"4"},
        {"rating":"5"},
        {"rating":"6"},
        {"rating":"7"},
        {"rating":"8"},
        {"rating":"9"},
        {"rating":"10"}
    ]
});

var performanceHours = Ext.create('Ext.data.Store', {
    fields: ['perf'],
    data : [
        {"perf":"8:00"},    {"perf":"8:15"},    {"perf":"8:30"},    {"perf":"8:45"},
        {"perf":"9:00"},    {"perf":"9:15"},    {"perf":"9:30"},    {"perf":"9:45"},
        {"perf":"10:00"},   {"perf":"10:15"},   {"perf":"10:30"},   {"perf":"10:45"},
        {"perf":"11:00"},   {"perf":"11:15"},   {"perf":"11:30"},   {"perf":"11:45"},
        {"perf":"12:00"},   {"perf":"12:15"},   {"perf":"12:30"},   {"perf":"12:45"},
        {"perf":"13:00"},   {"perf":"13:15"},   {"perf":"13:30"},   {"perf":"13:45"},
        {"perf":"14:00"},   {"perf":"14:15"},   {"perf":"14:30"},   {"perf":"14:45"},
        {"perf":"15:00"},   {"perf":"15:15"},   {"perf":"15:30"},   {"perf":"15:45"},
        {"perf":"16:00"},   {"perf":"16:15"},   {"perf":"16:30"},   {"perf":"16:45"},
        {"perf":"17:00"},   {"perf":"17:15"},   {"perf":"17:30"},   {"perf":"17:45"},
        {"perf":"18:00"},   {"perf":"18:15"},   {"perf":"18:30"},   {"perf":"18:45"},
        {"perf":"19:00"},   {"perf":"19:15"},   {"perf":"19:30"},   {"perf":"19:45"},
        {"perf":"20:00"},   {"perf":"20:15"},   {"perf":"20:30"},   {"perf":"20:45"},
        {"perf":"21:00"},   {"perf":"21:15"},   {"perf":"21:30"},   {"perf":"21:45"},
        {"perf":"22:00"},   {"perf":"22:15"},   {"perf":"22:30"},   {"perf":"22:45"},
        {"perf":"23:00"},   {"perf":"23:15"},   {"perf":"23:30"},   {"perf":"23:45"},
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
        style: 'width: 25%'
    },

    items: [{
        items: [
            {
                fieldLabel: 'Movie',
                name: 'movieTitle'
            },{
                fieldLabel: 'Cinema',
                name: 'cinemaTitle'
            }
        ]
    }, {
        items: [
            {
                xtype: 'combobox',
                fieldLabel: 'Age rating',
                name: 'ageRating',
                store: ageRatings,
                displayField: 'age',
                valueField: 'age'
            },{
                xtype: 'combobox',
                fieldLabel: 'Movie Rating',
                name: 'movieRating',
                store: movieRatings,
                displayField: 'rating',
                valueField: 'rating'
            }
        ]
    }, {
        items: [
            {
                fieldLabel: 'Performance start time',
                xtype: 'combobox',
                name: 'performanceStartTime',
                store: performanceHours,
                displayField: 'perf',
                valueField: 'perf'
            },{
                fieldLabel: 'Performance end time',
                xtype: 'combobox',
                name: 'performanceEndTime',
                store: performanceHours,
                displayField: 'perf',
                valueField: 'perf'
            }
        ]
    }, {
        items: [
            {
                xtype: 'radiogroup',
                fieldLabel: 'Movie Type',
                columns: 2,
                items: [
                    {boxLabel: '2D', name: 'threeD', inputValue: 'false', checked: true},
                    {boxLabel: '3D', name: 'threeD', inputValue: 'true'}
                ]
            }
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
                        method: 'POST',
                        headers: {'Content-Type': 'application/json'},
                        params: Ext.JSON.encode(formPanel.getValues()),
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
        }
    ]
});