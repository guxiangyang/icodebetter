var SideBar =  Vue.component('side-bar', {
	template:' <div class="sidebar"    :style="sidebarStyle" :data-color="backgroundColor" :data-image="backgroundImage"> <div class="sidebar-wrapper"><div class="logo">  <a href="#" class="simple-text"><div> '+iwb.logo+'{{title}}</div></a></div><slot name="content"></slot><ul class="nav">  <!--By default vue-router adds an active class to each route link. This way the links are colored when clicked-->  <slot> <sidebar-link v-for="(link,index) in sidebarLinks":key="link.name + index"                        :to="link.path"                        @click="closeNavbar"                        :link="link">            <i :class="link.icon"></i>            <p>{{link.name}}</p>          </sidebar-link>        </slot>      </ul>    </div>  </div>'
	//,components: {      SidebarLink    },
    ,props: {
      title: {
        type: String,
        default: 'iCodeBetter'
      },
      backgroundColor: {
        type: String,
        default: 'black',
        validator: (value) => {
          let acceptedValues = ['', 'blue', 'azure', 'green', 'orange', 'red', 'purple', 'black']
          return acceptedValues.indexOf(value) !== -1
        }
      },
      backgroundImage: {
        type: String,
        default: 'css/img/sidebar-2.jpg'
      },
      activeColor: {
        type: String,
        default: 'success',
        validator: (value) => {
          let acceptedValues = ['primary', 'info', 'success', 'warning', 'danger']
          return acceptedValues.indexOf(value) !== -1
        }
      },
      sidebarLinks: {
        type: Array,
        default: () => []
      },
      autoClose: {
        type: Boolean,
        default: true
      }
    },
    provide () {
      return {
        autoClose: this.autoClose
      }
    },
    computed: {
      sidebarStyle () {
        return {
          backgroundImage: `url(${this.backgroundImage})`
        }
      }
    }
  }
);