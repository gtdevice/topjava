var ajaxUrl = 'ajax/admin/meals/';
var datatableApi;

// $(document).ready(function () {
$(function () {
    datatableApi = $('#datatable').DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "date"
            },
            {
                "data": "meal"
            },
            {
                "data": "calories"
            },
            //{
            //    "data": "enabled"
            //},
            //{
            //    "data": "registered"
            //},
            {
                "defaultContent": "Edit",
                "orderable": false
            },
            {
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });
    makeEditable();
});