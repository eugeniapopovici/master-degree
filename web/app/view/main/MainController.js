/**
 * This class is the controller for the main view for the application. It is specified as
 * the "controller" of the Main view class.
 *
 * TODO - Replace this content of this view to suite the needs of your application.
 */
Ext.define('Cinema.view.main.MainController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.main',
    
    onItemSelected: function (sender, record) {
        var data = record.data;

        var movieId = data.id,
            cinemaId = null,
            movieName = data.name;
        var cinemaStore = Ext.create('Ext.data.Store', {
            autoLoad: false,
            proxy: {
                type: 'rest',
                method: 'GET',
                url: 'cinema/booking/cinemas/movieId/' + movieId,
                reader: {
                    type: 'json',
                    rootProperty: 'data'
                }
            }
        });
        cinemaStore.load();

        var performanceStore = Ext.create('Ext.data.Store', {
            autoLoad: false,
            proxy: {
                type: 'rest',
                url: 'cinema/booking/performances',
                reader: {
                    type: 'json',
                    rootProperty: 'data'
                }
            }
        });

        var performanceHours = Ext.create('Ext.data.Store', {
            fields: ['id', 'time'],
            data : [
                {"id":"1", "time":"9:00 - 12:00"},
                {"id":"2", "time":"13:00 - 16:00"},
                {"id":"3", "time":"17:00 - 21:00"}
            ]
        });
        cinemaStore.load();

        var categoryStore = Ext.create('Ext.data.Store', {
            autoLoad: false,
            proxy: {
                type: 'rest',
                method: 'GET',
                url: 'cinema/categories/all',
                reader: {
                    type: 'json',
                    rootProperty: 'data'
                }
            }
        });
        categoryStore.load();

        var cinema = Ext.define('Cinema.view.window.BookingWindow', {
            extend: 'Ext.window.Window',
            xtype: 'booking-window',

            title: 'Booking',
            height: 250,
            width: 410,
            layout: 'fit',
            items: {
                xtype: 'form',
                bodyPadding: 15,
                layout: 'anchor',
                defaults: {
                    allowBlank: false,
                    anchor: '100%'
                },
                items: [{
                        xtype: 'label',
                        text: Ext.util.Format.uppercase(movieName)
                    }, {
                        xtype: 'hiddenfield',
                        name: 'showing.movie.id',
                        value: movieId
                    }, {
                        xtype: 'combobox',
                        fieldLabel: 'Cinema',
                        name: 'showing.cinema.id',
                        store: cinemaStore,
                        displayField: 'cinemaName',
                        valueField: 'id',
                        listeners:{
                            scope: this,
                            'select': function (record) {
                                cinemaId: record.displayTplData[0].id;
                                performanceStore.getProxy().extraParams.movieId = movieId;
                                performanceStore.getProxy().extraParams.cinemaId = record.displayTplData[0].id;
                                // performanceStore.load();
                            }
                        }
                    }, {
                        xtype: 'combobox',
                        itemId: 'performanceCombo',
                        fieldLabel: 'Performance',
                        name: 'showing.performance.id',
                        store: performanceHours,
                        displayField: 'time',
                        valueField: 'id',
                        value: 1
                    }, {
                        xtype: 'combobox',
                        fieldLabel: 'Category',
                        name: 'category.id',
                        store: categoryStore,
                        displayField: 'categoryName',
                        valueField: 'id',
                        value: 1
                    }, {
                        xtype: 'datefield',
                        fieldLabel: 'For Date',
                        name: 'bookingForDate',
                        minValue: new Date()
                    }
                ],
                buttons: [{
                        text: 'Reset',
                        handler: function() {
                            this.up('form').getForm().reset();
                        }
                    },{
                        text: 'Submit',
                        handler: function () {
                            var formPanel = this.up('form');
                            console.log(formPanel);
                            console.log(formPanel.getValues());
                            if (formPanel.getForm().isValid()) {
                                var box = Ext.MessageBox.wait('Please wait...', 'Performing Actions');
                                Ext.Ajax.request({
                                    url: 'cinema/booking',
                                    method: 'POST',
                                    headers: {'Content-Type': 'application/json'},
                                    params: Ext.JSON.encode(formPanel.getValues()),
                                    success: function (result) {
                                        var response = Ext.decode(result.responseText);
                                        box.hide();
                                        if (response.success) {
                                            Ext.Msg.alert("Success", response.message, function () {
                                                formPanel.up('booking-window').close();
                                                Ext.getCmp('mainStore').getStore().reload();
                                                Ext.getCmp('bookingsStore').getStore().reload();
                                            });
                                        } else {
                                            Ext.Msg.alert("Failure", response.message, function () {
                                                formPanel.up('booking-window').close();
                                                Ext.getCmp('mainStore').getStore().reload();
                                                Ext.getCmp('bookingsStore').getStore().reload();
                                            });
                                        }
                                    },
                                    failure: function () {
                                        box.hide();
                                        Ext.Msg.alert("Failure", "Error on booking!", function () {
                                            formPanel.up('booking-window').close();
                                            Ext.getCmp('mainStore').getStore().reload();
                                            Ext.getCmp('bookingsStore').getStore().reload();
                                        });
                                    }
                                });
                            }
                        }
                    }
                ]
            }
        });
        Ext.create(cinema).show();
    },

    onConfirm: function (choice) {
        if (choice === 'yes') {
            //
        }
    }
});
