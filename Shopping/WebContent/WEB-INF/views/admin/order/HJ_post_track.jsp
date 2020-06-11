<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<div id="searchCates">
<!-- 模态框（Modal） -->
        <div class="modal fade" style="overflow:hidden" id="myModal_post_track"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            &times;
                        </button>
                        <nobr class="Foam-title" id="myModalLabelp">
                        </nobr>
                    </div>
                    <div class="modal-body" style="display:block;height:260px">
                      <div id="conHJ"></div>
                      <textArea id="postHj" style="width: 100%;height: 100%;"></textArea>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                        </button>
                        <button type="button" id="modal-change" class="btn btn-primary">
                            	下载结果
                        </button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
        </div>
</div>