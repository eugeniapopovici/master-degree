Ext.define('Cinema.view.main.Bookings', {
    extend: 'Ext.grid.Panel',
    xtype: 'bookingslist',

    requires: [
        'Cinema.store.Bookings'
    ],

    title: 'Bookings',

    id: 'mainBookings',

    ref: 'bookingsGrid',

    store: {
        type: 'bookings'
    },

    columns: [
        {
            xtype: 'hidden',
            dataIndex: 'id'
        }, {
            text: 'Customer',
            xtype: 'templatecolumn',
            sortable: false,
            flex: 0.2,
            align: 'left',
            tpl: new Ext.XTemplate('<b>Name: </b>{customer.customerName}<br>' +
                '<b>Phone: </b>{customer.customerPhone}<br>')
        }, {
            text: 'Cinema',
            xtype: 'templatecolumn',
            sortable: false,
            flex: 0.2,
            align: 'left',
            tpl: new Ext.XTemplate('<b>Cinema: </b>{showing.cinema.cinemaName}<br>' +
                '<b>Address: </b>{showing.cinema.cinemaAddress.city}, {showing.cinema.cinemaAddress.addressLine}<br>' +
                '<b>Phone: </b>{showing.cinema.cinemaPhone}<br>')
        }, {
            text: 'Movie',
            xtype: 'templatecolumn',
            sortable: false,
            flex: 0.2,
            align: 'left',
            tpl: new Ext.XTemplate('<b>{showing.movie.name}</b><br>' +
                '<b>Director: </b>{showing.movie.stageDirector}<br>' +
                '<b>Genre: </b>{showing.movie.genre}<br>' +
                '<b>Rating: </b>{showing.movie.rating}<br>' +
                '<b>{[this.getThreeD(values.showing.movie.threeD)]}</b>',
                {
                    getThreeD: function (threeD) {
                        return threeD ? "3D" : "2D";
                    }
                })
        }, {
            text: 'Booking',
            xtype: 'templatecolumn',
            sortable: false,
            flex: 0.2,
            align: 'left',
            tpl: new Ext.XTemplate('<b>Booking to date: </b>{bookingForDate.dayOfMonth}.{bookingForDate.monthValue}.{bookingForDate.year}<br>' +
                '<b>Category: </b>{category.categoryName}<br>')
        }
    ]

    // listeners: {
    //     select: 'onItemSelected'
    // }
});
