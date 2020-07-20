package pl.slowikowski.demo.productGroup.service;

//@SpringBootTest
//@ActiveProfiles("testing")
class ProductGroupServiceImplIntegrationTest {
//    @Autowired
//    private ProductGroupRepository groupRepository;
//    @Autowired
//    private ProductRepository productRepository;
//
//    private final ProductGroupMapper groupMapper = ProductGroupMapper.INSTANCE;
//
//    private final ProductMapper productMapper = ProductMapper.INSTANCE;
//
//    @Autowired
//    private ProductServiceImpl productService;
//
//    @Autowired
//    private ProductGroupServiceImpl groupService;
//
////    @BeforeEach
////    void setUp() {
////        groupRepository.save(getProductGroupForDataBase());
////        productRepository.save(getProductForDataBase());
////    }
//
//    @Test
//    @DisplayName("After test group which does not contain any products should contain exacly one product")
//    void should_update_group() {
//        //given
//        var defaultGroupDTO = groupService.saveProductGroup(getProductGroupDTOForDataBase());
//        var productDTO = productService.saveProduct(getProductDTOForDataBase());
//
//        //and
//        var targetGroupDTO = getProductGroupDTOForDataBase();
//        targetGroupDTO.setName("Target");
//        final int id = groupService.saveProductGroup(targetGroupDTO).getId();
//
//        //when
//        targetGroupDTO.setProducts(Set.of(getProductDTOForDataBase()));
//        var result = groupService.updateGroup(id, targetGroupDTO);
//        //then
//
//        assertThat(groupRepository.findAll()).hasSize(2);
//        assertThat(productService.findById(productDTO.getId()).getGroupId()).isEqualTo(id);
//    }
}
