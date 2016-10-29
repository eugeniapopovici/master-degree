Ext.define('Cinema.store.Bookings', {
    extend: 'Ext.data.Store',

    alias: 'store.bookings',

    id: 'bookingsStore',

    autoLoad: true,
    proxy: {
        type: 'rest',
        url: 'cinema/booking/all',
        reader: {
            type: 'json',
            rootProperty: 'data'
        }
    }

});
