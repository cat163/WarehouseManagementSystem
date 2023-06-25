   项目名称:仓库管理系统

​	技术栈：spring boot2.7.x,redis5,mybatis-plus,MySQL8.x,Swagger

由于要求只能写2个小时，一下是对该笔试项目的见解

- 对于题目二：  当有消息超过3小时未读取.发送短信给admin用户(短信可写伪代码)这个问题来说，可以使用 Redis + 定时任务实现  +  短信服务

- 对于题目一里面的出库，入库做了比较详细的编写 出库流程  出库 -> 门店商品减 -> 门店台账流水 -> 订单流水，入库 -> 总库商品减 门店商品加 -> 总库流水帐 门店台账流水 这里面花了很多时间去设计表与表之间的联系和结构 表结构在 src/main/java/com/whq/warehousemanagementsystem/sql 目录下，

- 题目一和题目二有些部分实现部分相似，就业务不同，其核心代码都差不多，比如分页查询，在题目一中就可以使用代码如下

  ```java
  @ApiOperation("查询商品")
      @GetMapping("/getCommodityListByPage")
      public Page<Commodity> getCommodityListByPage(CommodityQueryRequest commodityQueryRequest) {
          long current = 1;
          long pageSize = 10;
          if (commodityQueryRequest == null) {
              log.error("参数为空");
              return null;
          }
          Commodity commodity = new Commodity();
          current = commodityQueryRequest.getCurrent();
          pageSize = commodityQueryRequest.getPageSize();
          BeanUtils.copyProperties(commodityQueryRequest, commodity);
          QueryWrapper<Commodity> queryWrapper = new QueryWrapper<>();
          String commodityName = commodity.getCommodityName();
          if (commodityName != null) {
              queryWrapper.like("commodityName", commodityName);
          }
          queryWrapper.orderByDesc("createTime");
          Page<Commodity> page = commodityService.page(new Page<>(current, pageSize), queryWrapper);
          return page;
      }
  ```

等。

自我评价：在独立编写校写过三四个项目，并上线。在我github上也能看到，具备开发的基本要求，并且在校当任了期末课设项目小组组长，并期末评分第二，具有良好的学习能力，领导能力和BUG解决能力，性格开朗，外向，热爱技术，并且具有良好的编码习惯，可以在这个笔试项目中体现一些，最后希望能够获取这个实习机会。