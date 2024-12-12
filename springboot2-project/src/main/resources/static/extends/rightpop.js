layui.define(['layer'], function(exports){
  var layer = layui.layer;
  var obj = {
      rightPopupLayer: function (content='',width='800px') {
          layer.open({
            type: 1,
                title: "编辑角色",
              offset: ['0', '100%'],
              skin: 'layui-anim layui-anim-rl layui-layer-adminRight',
              shadeClose: true,
              content: content,
              shadeClose: true,
              btn: ['确定', '取消'],
              area: [width, '100%']
          })
          let op_width = $('.layui-anim-rl').outerWidth();
          $('.layui-layer-shade').off('click').on('click', function () {
              $('.layui-anim-rl').animate({left:'+='+op_width+'px'}, 300, 'linear', function () {
                  $('.layui-anim-rl').remove()
                  $('.layui-layer-shade').remove()
              })

          })
      }
  };
  exports('rightpop', obj);
});
