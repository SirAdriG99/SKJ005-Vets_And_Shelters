<script setup>
import { ref } from 'vue';
import CustomerService from "@/service/CustomerService";
import RecomendatorService from "@/service/RecomendatorService";
import AppointmentService from "@/service/AppointmentService";
import AnimalService from "@/service/AnimalService";

const dropdownItems = ref([
    { name: 'Option 1', code: 'Option 1' },
    { name: 'Option 2', code: 'Option 2' },
    { name: 'Option 3', code: 'Option 3' }
]);

const dropdownSpaces = ref([
    { name: 'Less than 30 square metres', code: 'less20m' },
    { name: 'Between 30 and 60 square metres', code: 'less60m' },
    { name: 'More than 60 square metres', code: 'more60m' }
]);

const dropdownAvailabilities = ref([
    { name: 'Less than 1 h / day', code: 'less1h' },
    { name: 'Between 1 and 3 h / day', code: 'less3h' },
    { name: 'More than 3 h / day', code: 'more3h' }
]);

const dropdowndangers = ref([
    { name: 'I do not care about the breed', code: true },
    { name: 'I do not want a dangerous breed', code: false }
]);

// const dropdownItem = ref(null);
const dropdownSpace = ref(null);
const radioGarden = ref(null);
const dropdownAvailability = ref(null);
const dropdowndangerous = ref(null);
const formData = ref({
    name: '',
    email: '',
    space: '',
    withGarden: '',
    availability: null,
    dangerous: null
})
const prediction = ref(null);
const animalIdPredicted = ref(null);
const animalPredicted = ref(null);
const predictions = ref(false);
const animalLoaded = ref(false);
const customerId = ref(null);
const error = ref(false);

const customerService = new CustomerService();
const recomendatorService = new RecomendatorService();
const appointmentService = new AppointmentService();
const animalService = new AnimalService();


const sendForm = () => {
    console.log(formData.value);
    let customerData = {
        name: formData.value.name,
        email: formData.value.email,
    }
    let response = customerService.storeCustomer(customerData);
    if(response.status === "OK"){
        console.log("Customer created");
        customerId.value = response.id;
        predictions.value = true;
        error.value = false;
        predict(formData.value);
    } else {
        console.log("Customer not created");
        predictions.value = false;
        error.value = true;
        formData.value = {
            name: '',
            email: '',
            space: '',
            withGarden: '',
            availability: null,
            dangerous: null
        }
    }
}

const predict = (formData) => {
  let query = {
    space: formData.space,
    withGarden: formData.withGarden,
    availability: formData.availability,
    dangerous: formData.dangerous
  }
  recomendatorService.sendData(query)
  recomendatorService.receiveData().then((response) => {
    animalIdPredicted.value = response.data;
  }).catch((error) => {
    console.log(error);
    error.value = true;
  })
  if(!error) {
    loadAnimal();
  }
}

const loadAnimal = () => {
  animalService.getAnimal(animalPredicted.value).then((response) => {
    animalPredicted.value = response.data;
    animalLoaded.value = true;
  }).catch((error) => {
    console.log(error);
    error.value = true;
  })
}

// import { useLayout } from '@/layout/composables/layout';
// import { computed } from 'vue';

// const { layoutConfig } = useLayout();

// const logoUrl = computed(() => {
//     return `layout/images/${layoutConfig.darkTheme.value ? 'logo-white' : 'logo-dark'}.svg`;
// });

</script>

<template>
    <div class="surface-ground flex align-items-center justify-content-center min-h-screen min-w-screen overflow-hidden">
        <div class="col-12 md:col-6">
            <!-- <img :src="logoURL" alt="V&S logo" class="mb-5 w-6rem flex-shrink-0" /> -->
                <div className="card">
                    <h5>Preference form</h5>
                    <p>Please fill the following form so we can evaluate which dogs can match with your availability.</p>
                </div>
                <div class="card p-fluid" v-show="!predictions">
                    <h5>Your preferences</h5>
                  <h4 v-if="error">There was an unexpected errror</h4>
                    <div class="field grid">
                        <label for="name3" class="col-12 mb-2 md:col-2 md:mb-0">Name</label>
                        <div class="col-12 md:col-10">
                            <InputText id="name3" type="text" v-model="formData.name"/>
                        </div>
                    </div>
                    <div class="field grid">
                        <label for="email3" class="col-12 mb-2 md:col-2 md:mb-0">Email</label>
                        <div class="col-12 md:col-10">
                            <InputText id="email3" type="text" v-model="formData.email"/>
                        </div>
                    </div>
                    <!-- <div class="field grid">
                        <label for="state" class="col-12 mb-2 md:col-2 md:mb-0">State</label>
                        <div class="col-12 md:col-10">
                            <Dropdown id="state" v-model="dropdownItem" :options="dropdownItems" optionLabel="name" placeholder="Select One"></Dropdown>
                        </div>
                    </div> -->
                    <div class="field grid">
                        <label for="space" class="col-12 mb-2 md:col-2 md:mb-0">Space</label>
                        <div class="col-12 md:col-10">
                            <Dropdown id="space" v-model="formData.space" :options="dropdownSpaces" optionLabel="name" placeholder="Select One"></Dropdown>
                        </div>
                    </div>
                    <div class="grid">
                        <div class="col-12 md:col-5">
                            <div class="field-radiobutton mb-0">
                                <RadioButton id="option1" name="garden" value="withgarden" v-model="formData.withGarden" />
                                <label for="option1">With garden</label>
                            </div>
                        </div>
                        <div class="col-12 md:col-5">
                            <div class="field-radiobutton mb-0">
                                <RadioButton id="option2" name="garden" value="withoutgarden" v-model="formData.withGarden" />
                                <label for="option2">Without garden</label>
                            </div>
                        </div>
                    </div>
                    <div class="field grid">
                        <label for="availability" class="col-12 mb-2 md:col-2 md:mb-0">Availability</label>
                        <div class="col-12 md:col-10">
                            <Dropdown id="availability" v-model="formData.availability" :options="dropdownAvailabilities" optionLabel="name" placeholder="Select One"></Dropdown>
                        </div>
                    </div>
                    <div class="field grid">
                        <label for="dangerous" class="col-12 mb-2 md:col-2 md:mb-0">Dangerous</label>
                        <div class="col-12 md:col-10">
                            <Dropdown id="dangerous" v-model="formData.dangerous" :options="dropdowndangers" optionLabel="name" placeholder="Select One"></Dropdown>
                        </div>
                    </div>
                    <div class="card">
                        <span class="p-buttonset">
                            <Button label="Send" icon="pi pi-check" @click="sendForm"/>
                            &nbsp;
                            <Button label="Cancel" icon="pi pi-times" @click="returnToHome"/>
                        </span>
                    </div>
                </div>
                <div class="card p-fluid" v-show="predictions">
                    <h5>Do you want to adopt it?</h5>
                    <div class="field grid">
                        <label for="name3" class="col-12 mb-2 md:col-2 md:mb-0">Name</label>
                        <div class="col-12 md:col-10">
                            <InputText id="name3" type="text" v-model="formData.name"/>
                        </div>
                    </div>
                    <div class="field grid">
                        <label for="email3" class="col-12 mb-2 md:col-2 md:mb-0">Email</label>
                        <div class="col-12 md:col-10">
                            <InputText id="email3" type="text" v-model="formData.email"/>
                        </div>
                    </div>
                    <!-- <div class="field grid">
                        <label for="state" class="col-12 mb-2 md:col-2 md:mb-0">State</label>
                        <div class="col-12 md:col-10">
                            <Dropdown id="state" v-model="dropdownItem" :options="dropdownItems" optionLabel="name" placeholder="Select One"></Dropdown>
                        </div>
                    </div> -->
                    <div class="field grid">
                        <label for="space" class="col-12 mb-2 md:col-2 md:mb-0">Space</label>
                        <div class="col-12 md:col-10">
                            <Dropdown id="space" v-model="formData.space" :options="dropdownSpaces" optionLabel="name" placeholder="Select One"></Dropdown>
                        </div>
                    </div>
                    <div class="grid">
                        <div class="col-12 md:col-5">
                            <div class="field-radiobutton mb-0">
                                <RadioButton id="option1" name="garden" value="withgarden" v-model="formData.withGarden" />
                                <label for="option1">With garden</label>
                            </div>
                        </div>
                        <div class="col-12 md:col-5">
                            <div class="field-radiobutton mb-0">
                                <RadioButton id="option2" name="garden" value="withoutgarden" v-model="formData.withGarden" />
                                <label for="option2">Without garden</label>
                            </div>
                        </div>
                    </div>
                    <div class="field grid">
                        <label for="availability" class="col-12 mb-2 md:col-2 md:mb-0">Availability</label>
                        <div class="col-12 md:col-10">
                            <Dropdown id="availability" v-model="formData.availability" :options="dropdownAvailabilities" optionLabel="name" placeholder="Select One"></Dropdown>
                        </div>
                    </div>
                    <div class="field grid">
                        <label for="dangerous" class="col-12 mb-2 md:col-2 md:mb-0">Dangerous</label>
                        <div class="col-12 md:col-10">
                            <Dropdown id="dangerous" v-model="formData.dangerous" :options="dropdowndangers" optionLabel="name" placeholder="Select One"></Dropdown>
                        </div>
                    </div>
                    <div class="card">
                        <span class="p-buttonset">
                            <Button label="Send" icon="pi pi-check" @click="sendForm"/>
                            &nbsp;
                            <Button label="Cancel" icon="pi pi-times" @click="returnToHome"/>
                        </span>
                    </div>
                </div>
               <div class="card p-fluid" v-show="predictions&&animalLoaded">
                    <h5>Horizontal</h5>
                    <div class="field grid">
                        <label for="name3" class="col-12 mb-2 md:col-2 md:mb-0">Name</label>
                        <div class="col-12 md:col-10">
                            <InputText id="name3" type="text" v-model="formData.name"/>
                        </div>
                    </div>
                    <div class="field grid">
                        <label for="email3" class="col-12 mb-2 md:col-2 md:mb-0">Email</label>
                        <div class="col-12 md:col-10">
                            <InputText id="email3" type="text" v-model="formData.email"/>
                        </div>
                    </div>
                    <!-- <div class="field grid">
                        <label for="state" class="col-12 mb-2 md:col-2 md:mb-0">State</label>
                        <div class="col-12 md:col-10">
                            <Dropdown id="state" v-model="dropdownItem" :options="dropdownItems" optionLabel="name" placeholder="Select One"></Dropdown>
                        </div>
                    </div> -->
                    <div class="field grid">
                        <label for="space" class="col-12 mb-2 md:col-2 md:mb-0">Space</label>
                        <div class="col-12 md:col-10">
                            <Dropdown id="space" v-model="formData.space" :options="dropdownSpaces" optionLabel="name" placeholder="Select One"></Dropdown>
                        </div>
                    </div>
                    <div class="grid">
                        <div class="col-12 md:col-5">
                            <div class="field-radiobutton mb-0">
                                <RadioButton id="option1" name="garden" value="withgarden" v-model="formData.withGarden" />
                                <label for="option1">With garden</label>
                            </div>
                        </div>
                        <div class="col-12 md:col-5">
                            <div class="field-radiobutton mb-0">
                                <RadioButton id="option2" name="garden" value="withoutgarden" v-model="formData.withGarden" />
                                <label for="option2">Without garden</label>
                            </div>
                        </div>
                    </div>
                    <div class="field grid">
                        <label for="availability" class="col-12 mb-2 md:col-2 md:mb-0">Availability</label>
                        <div class="col-12 md:col-10">
                            <Dropdown id="availability" v-model="formData.availability" :options="dropdownAvailabilities" optionLabel="name" placeholder="Select One"></Dropdown>
                        </div>
                    </div>
                    <div class="field grid">
                        <label for="dangerous" class="col-12 mb-2 md:col-2 md:mb-0">Dangerous</label>
                        <div class="col-12 md:col-10">
                            <Dropdown id="dangerous" v-model="formData.dangerous" :options="dropdowndangers" optionLabel="name" placeholder="Select One"></Dropdown>
                        </div>
                    </div>
                    <div class="card">
                        <span class="p-buttonset">
                            <Button label="Send" icon="pi pi-check" @click="sendForm"/>
                            &nbsp;
                            <Button label="Cancel" icon="pi pi-times" @click="returnToHome"/>
                        </span>
                    </div>
                </div><div class="card p-fluid" v-show="!predictions">
                    <h5>Horizontal</h5>
                    <div class="field grid">
                        <label for="name3" class="col-12 mb-2 md:col-2 md:mb-0">Name</label>
                        <div class="col-12 md:col-10">
                            <InputText id="name3" type="text" v-model="formData.name"/>
                        </div>
                    </div>
                    <div class="field grid">
                        <label for="email3" class="col-12 mb-2 md:col-2 md:mb-0">Email</label>
                        <div class="col-12 md:col-10">
                            <InputText id="email3" type="text" v-model="formData.email"/>
                        </div>
                    </div>
                    <!-- <div class="field grid">
                        <label for="state" class="col-12 mb-2 md:col-2 md:mb-0">State</label>
                        <div class="col-12 md:col-10">
                            <Dropdown id="state" v-model="dropdownItem" :options="dropdownItems" optionLabel="name" placeholder="Select One"></Dropdown>
                        </div>
                    </div> -->
                    <div class="field grid">
                        <label for="space" class="col-12 mb-2 md:col-2 md:mb-0">Space</label>
                        <div class="col-12 md:col-10">
                            <Dropdown id="space" v-model="formData.space" :options="dropdownSpaces" optionLabel="name" placeholder="Select One"></Dropdown>
                        </div>
                    </div>
                    <div class="grid">
                        <div class="col-12 md:col-5">
                            <div class="field-radiobutton mb-0">
                                <RadioButton id="option1" name="garden" value="withgarden" v-model="formData.withGarden" />
                                <label for="option1">With garden</label>
                            </div>
                        </div>
                        <div class="col-12 md:col-5">
                            <div class="field-radiobutton mb-0">
                                <RadioButton id="option2" name="garden" value="withoutgarden" v-model="formData.withGarden" />
                                <label for="option2">Without garden</label>
                            </div>
                        </div>
                    </div>
                    <div class="field grid">
                        <label for="availability" class="col-12 mb-2 md:col-2 md:mb-0">Availability</label>
                        <div class="col-12 md:col-10">
                            <Dropdown id="availability" v-model="formData.availability" :options="dropdownAvailabilities" optionLabel="name" placeholder="Select One"></Dropdown>
                        </div>
                    </div>
                    <div class="field grid">
                        <label for="dangerous" class="col-12 mb-2 md:col-2 md:mb-0">Dangerous</label>
                        <div class="col-12 md:col-10">
                            <Dropdown id="dangerous" v-model="formData.dangerous" :options="dropdowndangers" optionLabel="name" placeholder="Select One"></Dropdown>
                        </div>
                    </div>
                    <div class="card">
                        <span class="p-buttonset">
                            <Button label="Send" icon="pi pi-check" @click="sendForm"/>
                            &nbsp;
                            <Button label="Cancel" icon="pi pi-times" @click="returnToHome"/>
                        </span>
                    </div>
                </div>

          <div class="col-12 mt-5 text-center">
            <i class="pi pi-fw pi-arrow-left text-blue-500 mr-2" style="vertical-align: center"></i>
            <router-link to="/landing" class="text-blue-500">Go to landing page</router-link>
          </div>
        </div>
    </div>
    
</template>
