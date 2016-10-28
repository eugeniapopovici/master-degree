/**
 * This view is an example list of people.
 */
Ext.define('Cinema.view.main.List', {
    extend: 'Ext.grid.Panel',
    xtype: 'mainlist',

    requires: [
        'Cinema.store.Movies'
    ],

    title: 'Movies',

    ref: 'moviesGrid',

    id: 'mainCinema',

    store: {
        type: 'movies'
    },

    columns: [
        {
            xtype: 'hidden',
            dataIndex: 'id'
        }, {
            text: 'Description',
            xtype: 'templatecolumn',
            sortable: false,
            flex: 1,
            align: 'left',
            tpl: new Ext.XTemplate('<b>{name}</b><br>' +
                '<b>Description: </b>{description}<br>' +
                '<b>Release Date: </b>{releaseDate.dayOfMonth}.{releaseDate.monthValue}.{releaseDate.year}<br>' +
                '<b>Duration: </b>{duration}<br>' +
                '<b>Genre: </b>{genre}<br>' +
                '<b>Director: </b>{stageDirector}<br>' +
                '<b>{[this.getThreeD(values.threeD)]}</b>',
                {
                    getThreeD: function (threeD) {
                        console.log(threeD);
                        return threeD ? "3D" : "2D";
                    }
                })
        }
    ],

    listeners: {
        select: 'onItemSelected'
    }
});
